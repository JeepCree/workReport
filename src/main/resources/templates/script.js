var currentDate = new Date();
var formattedDate = currentDate.toISOString().split('T')[0];
document.getElementById("Date").value = formattedDate;