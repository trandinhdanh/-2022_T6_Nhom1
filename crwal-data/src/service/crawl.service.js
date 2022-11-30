const request = require("request-promise");
const fs = require("fs");
const { Parser } = require("json2csv");

const fields = [
  "trang",
  "ngay",
  "tinh",
  "loaive",
  "giaidb",
  "giai1",
  "giai2",
  "giai3",
  "giai4",
  "giai5",
  "giai6",
  "giai7",
  "giai8",
  ",",
];

async function crawlerXosoNet(options, website, currentDate, file) {
  const opts = { fields };
  try {
    // Lấy dữ liệu từ trang crawl đã được parseDOM
    var $ = await request(options);
  } catch (error) {
    return error;
  }

  /* Phân tích các table và sau đó lấy các posts.
         Mỗi table là một chương 
      */
  var obj = {
    data: [],
  };
  let trang = website;
  let ngay = currentDate;
  let tinh = null;
  let loaive = null;
  let giaidb = null;
  let giai1 = null;
  let giai2 = null;
  let giai3 = null;
  let giai4 = null;
  let giai5 = null;
  let giai6 = null;
  let giai7 = null;
  let giai8 = null;

  // tìm kiếm theo tỉnh miền Nam, Trung
  $(".rightcl").each((index, el) => {
    // tìm thuộc tính của mỗi giải để lấy giá trị
    tinh = $(el).find("tbody tr td.tinh a").text().trim();
    loaive = $(el).find("tbody tr td.matinh").text().trim();
    giaidb = $(el).find("tbody tr td.giaidb").text().trim();
    giai1 = $(el).find("tbody tr td.giai1").text().trim();
    giai2 = $(el).find("tbody tr td.giai2").text().trim();
    giai3 = replaceString($(el).find("tbody tr td.giai3").text().trim(), 5);
    giai4 = replaceString($(el).find("tbody tr td.giai4").text().trim(), 5);
    giai5 = $(el).find("tbody tr td.giai5").text().trim();
    giai6 = replaceString($(el).find("tbody tr td.giai6").text().trim(), 4);
    giai7 = $(el).find("tbody tr td.giai7").text().trim();
    giai8 = $(el).find("tbody tr td.giai8").text().trim();

    // thêm giá trị vào object
    obj.data.push({
      trang,
      ngay,
      tinh,
      loaive,
      giaidb,
      giai1,
      giai2,
      giai3,
      giai4,
      giai5,
      giai6,
      giai7,
      giai8,
    });
  });

  // tìm kiếm theo tỉnh miền Bắc
  $(".bkqtinhmienbac").each((index, el) => {
    ngay = currentDate;
    tinh = "Miền Bắc";
    loaive = $(el).find("tbody tr td.matinh").text().trim();
    giaidb = $(el).find("tbody tr td.giaidb").text().trim();
    giai1 = $(el).find("tbody tr td.giai1").text().trim();
    giai2 = replaceString($(el).find("tbody tr td.giai2").text().trim(), 5);
    giai3 = replaceString($(el).find("tbody tr td.giai3").text().trim(), 5);
    giai4 = replaceString($(el).find("tbody tr td.giai4").text().trim(), 4);
    giai5 = replaceString($(el).find("tbody tr td.giai5").text().trim(), 4);
    giai6 = replaceString($(el).find("tbody tr td.giai6").text().trim(), 3);
    giai7 = replaceString($(el).find("tbody tr td.giai7").text().trim(), 2);
    giai8 = 0;

    obj.data.push({
      trang,
      ngay,
      tinh,
      loaive,
      giaidb,
      giai1,
      giai2,
      giai3,
      giai4,
      giai5,
      giai6,
      giai7,
      giai8,
    });
  });

  // lưu thành file json
  fs.writeFileSync("data.json", JSON.stringify(obj), "utf8");
  // chuyển từ file json thành file csv
  var isWrite = false;
  try {
    const parser = new Parser(opts);
    const csv = parser.parse(obj.data);
    fs.writeFileSync(file, csv);
    return (isWrite = true);
  } catch (err) {
    return (isWrite = false);
  }
}

// cắt chuỗi của giải
function replaceString(str, length) {
  let result = "";
  let j = length;
  for (let i = 0; i < str.length; i += length) {
    const temp = str.substring(i, j);
    result += temp + " ";
    j += length;
  }
  return result.trim();
}

module.exports = {
  crawlerXosoNet
};
