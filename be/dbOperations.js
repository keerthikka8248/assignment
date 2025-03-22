var config = require('./dbConfig')
var mysql = require(mysql);

async  function  getBooks() {
    try {
      let  pool = await  sql.connect(config);
      let  books = await  pool.request().query("SELECT * from Books");
      return  books.recordsets;
    }
    catch (error) {
      console.log(error);
    }
  }

  async  function  getBook(bookId) {
    try {
      let  pool = await  sql.connect(config);
      let  book = await  pool.request()
      .input('input_parameter', sql.Int, bookId)
      .query("SELECT * from Books where Id = @input_parameter");
      return  book.recordsets;
    }
    catch (error) {
      console.log(error);
    }
  }

  async  function  addBook(book) {
    try {
      let  pool = await  sql.connect(config);
      let  insertBook = await  pool.request()
    .input('Id', sql.Int, book.Id)
    .input('Title', sql.NVarChar, book.Title)
    .input('Message', sql.NVarChar, book.Description)
    .input('Author', sql.NVarChar, book.Author)
    .execute('InsertBook');
    return  insertBook.recordsets;
  }
  catch (err) {
    console.log(err);
  }
}

module.exports = {
    getBooks : getBooks,
    getBook : getBook,
    addBook : addBook
}