const config = require("../../config.json");
const mysql = require("mysql2");
const fs = require("fs");

const { host, port, user, password, database } = config.database;
let connection = mysql.createConnection({
  host,
  port,
  user,
  password,
  database,
});

async function setLocalFile() {
  let sql = `SET GLOBAL local_infile = TRUE;`;
  return doQuery(sql);
}

async function getDataTable(table) {
  let sql = `SELECT * FROM ` + `${String(table)}`;
  return doQuery(sql);
}

async function insertFileLog(date, fileName, filePath, description, status) {
  let sql = `INSERT INTO file_log(log_date, file_name, file_path, log_description, status) VALUES('${date}', '${fileName}', '${filePath}', '${description}', '${status}')`;
  connection.query(sql);
}

async function updateFileLog(description, status) {
  let sql = `update file_log 
                set status = '${status}', log_description = '${description}'
                ORDER BY file_sk DESC LIMIT 1;`;
  connection.query(sql);
}

async function getFileLogToday(currentDate, status) {
  let sql = ` SELECT count(*) as countValue FROM file_log
                WHERE log_date = '${currentDate}' AND status = '${status}' `;

  connection.query(sql, (error, data) => {
    if (data[0].countValue == 0) {
      return true;
    }
  });
}

async function insertStaging(filePath) {
  connection.query(
    {
      sql: `LOAD DATA LOCAL INFILE "${filePath}"
                INTO TABLE staging
                COLUMNS TERMINATED BY "," 
                OPTIONALLY ENCLOSED BY '"'
                ESCAPED BY '"'
                LINES TERMINATED BY '\n' 
                IGNORE 1 LINES;`,
      values: [],
      infileStreamFactory: () => fs.createReadStream(filePath),
    },
    (error) => {
      if (!error) {
        return true;
      } else {
        return false;
      }
    }
  );
}

async function updateStaging(dim, staging_k, dim_sk, dim_value) {
  let sql = `UPDATE staging AS st JOIN ${dim} AS dt ON st.${staging_k} = dt.${dim_sk}
                SET st.${staging_k} = dt.${dim_value};`;
  connection.query(sql, (error) => {
    if (!error) {
      return true;
    } else {
      return false;
    }
  });
}

async function updateValidWarehouse() {
  let sql = `UPDATE warehouse JOIN staging ON staging.tinh = warehouse.tinh \
                 SET warehouse.is_valid = 0;`;
  connection.query(sql, (error) => {
    if (error) {
      return false;
    } else {
      return true;
    }
  });
}

async function copyData() {
  let sql = `INSERT INTO warehouse (website, date, city, type, giai_db, giai_1, giai_2, giai_3, giai_4, giai_5, giai_6, giai_7, giai_8)
                SELECT website, ngay, tinh, loai_ve, giai_db, giai_1, giai_2, giai_3, giai_4, giai_5, giai_6, giai_7, giai_8 FROM staging;`;
  connection.query(sql, (error) => {
    if (!error) {
      connection.query("TRUNCATE staging;");
      closeConnect();
    } else {
    }
  });
}

async function closeConnect() {
  connection.end();
}

async function doQuery(queryToDo) {
  let pro = new Promise((resolve, reject) => {
    let query = queryToDo;
    connection.query(query, function (err, result) {
      if (err) throw err;
      resolve(result);
    });
  });
  return pro.then((val) => {
    return val;
  });
}

module.exports = {
  getFileLogToday,
  getDataTable,
  setLocalFile,
  insertFileLog,
  updateFileLog,
  insertStaging,
  updateStaging,
  updateValidWarehouse,
  copyData,
  closeConnect,
};
