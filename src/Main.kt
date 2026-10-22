fun main() {
   val catalog = listOf(
       Book("Мастер и Маргарита", "Булгаков", 1967, Genre.FICTION, BookStatus.AVAILABLE),
       Book("Преступление и наказание", "Достоевский", 1866, Genre.FICTION, BookStatus.BORROWED),
       Book("Краткая история времени", "Хокинг", 1988, Genre.SCIENTIFIC, BookStatus.AVAILABLE),
       Book("Гарри Поттер", "Роулинг", 1997, Genre.FANTASY, BookStatus.AVAILABLE),
       Book("Убийство в Восточном экспрессе", "Кристи", 1934, Genre.DETECTIVE, BookStatus.LOST),
       Book("Дюна", "Герберт", 1965, Genre.FANTASY, BookStatus.RESERVED),
       Book("Солярис", "Лем", 1961, Genre.FANTASY, BookStatus.AVAILABLE),
       Book("Малыш и Карлсон", "Линдгрен", 1955, Genre.CHILDREN, BookStatus.BORROWED),
       Book("Стив Джобс", "Айзексон", 2011, Genre.BIOGRAPHY, BookStatus.ARCHIVED),
       Book("Марсианин", "Вейер", 2011, Genre.SCIENTIFIC, BookStatus.AVAILABLE)
   )
    printCat(catalog)
    stat(catalog)
    allAvailable(catalog)
    inGenres(catalog, "fantasy")
    inYear(catalog, 1977)
    borrowBook(catalog, 1)
    borrowBook(catalog, 8)
    backBook(catalog, 2)
    backBook(catalog, 5)
    lostBook(catalog, 6)
    resBook(catalog, 7)
}
fun printCat(catalog: List<Book>) {
    println("№ | Название | Автор | Год | Жанр | Статус")
    println("-".repeat(80))
    for (i in catalog.indices) {
        val book = catalog[i]
        println("${i + 1} | ${book.name} | ${book.author} | ${book.year} | ${book.genre} | ${book.status.displayName}")
    }
}
fun stat(catalog: List<Book>) {
    println("---  Статистика  ---")
    println("По статусам:")
    for (status in BookStatus.values()) {
        var count = 0
        for (book in catalog) {
            when (book.status) {
                status -> count++
                else -> {}
            }
        }
        println("  ${status.displayName}: $count")
    }
    println("По жанрам:")
    for (genre in Genre.values()) {
        var count = 0
        for (book in catalog) {
            when (book.genre) {
                genre -> count++
                else -> {}
            }
        }
        if (count > 0) println("  $genre: $count")
    }
    var old = catalog[0]
    var new = catalog[0]
    for (book in catalog) {
        if (book.year < old.year) old = book
        if (book.year > new.year) new = book
    }
    println("Самая старая книга: ${old.name} (${old.year})")
    println("Самая новая книга: ${new.name} (${new.year})")
    var sum = 0
    for (book in catalog) {
        sum += book.year
    }
    val avg = sum.toDouble() / catalog.size
    println("Средний год: ${String.format("%.1f", avg)}")
}
fun allAvailable(catalog: List<Book>) {
    println("---  Доступные книги  ---")
    for (book in catalog) {
        if (book.status == BookStatus.AVAILABLE) {
            println(book.name)
        }
    }
}
fun inGenres(catalog: List<Book>, genreInp: String) {
    println("---  Книги в жанре ${genreInp} ---")
    for (book in catalog) {
        if (book.genre.name == genreInp.uppercase()) {
            println(book.name)
        }
    }
}
fun inYear(catalog: List<Book>, yearInp: Int) {
    println("---  Книги, выпущенные после ${yearInp} года ---")
    for (book in catalog) {
        if (book.year > yearInp) {
            println(book.name)
        }
    }
}
fun borrowBook(catalog: List<Book>, num: Int) {
    val index = num - 1
    val book = catalog[index]
    if (book.status == BookStatus.AVAILABLE) {
        book.status = BookStatus.BORROWED
        println("Книга ${book.name} успешно выдана!")
    }
    else {
        println("Книга ${book.name} не может быть выдана. Её статус: ${book.status.displayName}")
    }
}
fun backBook(catalog: List<Book>, num: Int) {
    val index = num - 1
    val book = catalog[index]
    if (book.status == BookStatus.BORROWED) {
        book.status = BookStatus.AVAILABLE
        println("Книга ${book.name} успешно возвращена!")
    }
    else {
        println("Книга ${book.name} не может быть возвращена. Её статус: ${book.status.displayName}")
    }
}
fun lostBook(catalog: List<Book>, num: Int) {
    val index = num - 1
    val book = catalog[index]
    if (index in catalog.indices) {
        book.status = BookStatus.LOST
        println("Книга ${book.name} теперь потеряна.")
    }
}
fun resBook(catalog: List<Book>, num: Int) {
    val index = num - 1
    val book = catalog[index]
    if (index in catalog.indices) {
        book.status = BookStatus.RESERVED
        println("Книга ${book.name} успешно зарезервирована!")
    }
}