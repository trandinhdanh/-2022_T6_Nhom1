const cheerio = require("cheerio");
const db = require("../db/connect");
const crawlService = require("../service/crawl.service");


var date = new Date();
var currentDate = date.getFullYear() + "-" + (date.getUTCMonth() + 1) + "-" + date.getDate();

async function crawlDB() {
  let fileConfig = await db.getDataTable("file_config");
  
  if (db.getFileLogToday(fileConfig[0].date_config, "TRANSFORM SUCCESS")){
    const fileName = fileConfig[0].date_config + "-" + fileConfig[0].website + ".csv";
    const filePath = fileConfig[0].file_folder + fileName;
    db.insertFileLog(fileConfig[0].date_config, fileName, filePath, "", "EXTRACT START");
    
    if (fileConfig[0].website = "www.xoso.net"){
      const URL = `${fileConfig[0].url}`;
      const options = {
        uri: URL,
        transform: function (body) {
          //Khi lấy dữ liệu từ trang thành công nó sẽ tự động parse DOM
          return cheerio.load(body);
        },
      };

      crawlService.crawlerXosoNet(options, fileConfig[0].website, fileConfig[0].date_config, filePath)
      .then (isWrite =>{
        if (isWrite){
          db.updateFileLog("Lấy dữ liệu thành công!", "EXTRACT READY");

          if (db.getFileLogToday(fileConfig[0].date_config, "EXTRACT READY")){
            db.setLocalFile();
            
            if (db.insertStaging(filePath)){
              db.updateFileLog("Thêm dữ liệu vào Staging thành công!", "STAGING READY");

              if (db.updateStaging("website_dim", "website", "domain", "web_sk") && 
                  db.updateStaging("city_dim", "tinh", "city_name", "city_sk") &&
                  db.updateStaging("date_dim", "ngay", "full_date", "date_sk"))
              {

                if (db.getFileLogToday(fileConfig[0].date_config, "STAGING READY")){
                  if (db.updateValidWarehouse()){
                    db.copyData();
                    db.updateFileLog("Chuyển dữ liệu sang WAREHOUSE thành công!", "TRANSFORM SUCCESS");
                  } else{
                    db.updateFileLog("Lỗi chuyển dữ liệu sang WAREHOUSE!", "TRANSFORM FAIL");
                  }
                } else{
                  db.updateFileLog("Cập nhật SK thành công!", "STAGING FAIL");
                  return;
                }
              } else{
                return;
              }
            }
            else {
              db.updateFileLog("Lỗi thêm dữ vào STAGING!", "FAIL");
              return;
            }
          }else{
            return;
          }
        }
      }).catch(error =>{
        db.updateFileLog("Lấy dữ liệu thất bại!", "EXTRACT FAIL");
        return;
      });
    }

  }else{
    return;
  }
};

crawlDB();