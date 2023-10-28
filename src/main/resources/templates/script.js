// Получаем текущую дату
var currentDate = new Date();

// Преобразуем текущую дату в строку формата "ГГГГ-ММ-ДД"
var formattedDate = currentDate.toISOString().split('T')[0];

// Устанавливаем значение поля ввода date
document.getElementById("Date").value = formattedDate;