// var data = [
//     {"id":270,"date":"2024-04-29T11:00:00","isBooked":true},
//     {"id":303,"date":"2024-05-02T08:00:00","isBooked":true},
//     {"id":278,"date":"2024-04-30T07:00:00","isBooked":true},
//     {"id":325,"date":"2024-05-04T06:00:00","isBooked":true}
//   ];

async function getCurrentAppointments() {
    return fetch('http://localhost:8080/get_current_appointments')
        .then(response => {
            // console.log(response);
            if (!response.ok) {
                throw new Error('Failed to fetch appointments');
            }
            return response.json();
        })
        .catch(error => {
            console.error('Error fetching appointments:', error);
            return []; // Возвращаем пустой массив в случае ошибки
        });
}

  

  // Функция для форматирования даты
  function formatDate(dateStr) {
    var date = new Date(dateStr);
    var options = { day: 'numeric', month: 'long', hour: 'numeric', minute: 'numeric' };
    return date.toLocaleDateString('ru', options);
  }

  // Функция для генерации списка времен
  async function generateList() {
    var data = await getCurrentAppointments();
    data.sort(function(a, b) {
        return a.id - b.id;
    });
    if (data.length > 0) {
        var timesDiv = document.getElementById("times");
        var ul = document.createElement("ul");
        ul.style.padding = "20px";
        ul.style.listStyleType = "none";
        ul.style.backgroundColor = "rgba(74, 74, 196, 0.651)";
        ul.style.borderRadius = "15px";
        ul.style.border = "3px solid black";
        data.forEach(function(item) {
            var li = document.createElement("li");
            li.style.marginTop = "10px";
            li.style.border = "3px solid black";
            li.style.padding = "5px";
            li.style.fontWeight = "bold";
            li.style.backgroundColor = "white";
            li.style.borderRadius = "10px";
            li.id = "item-" + item.id; // Устанавливаем id для элемента li

            var dateText = formatDate(item.date);
            li.textContent = dateText;

            var closeButton = document.createElement("button");
            closeButton.style.marginLeft = "20px";
            closeButton.style.backgroundColor = "red";
            closeButton.style.color = "white";
            closeButton.textContent = "✖";
            closeButton.setAttribute("id", "button-" + item.id); // Устанавливаем id для кнопки

            closeButton.addEventListener("click", async function() {
                var check = await delete_appointment_from_current(item.id);
                if (check) {
                    location.reload();
                    alert("Запись удалена");
                }
            });

            li.appendChild(closeButton);

            ul.appendChild(li);
        });
        timesDiv.appendChild(ul);
    } else {
        var timesDiv = document.getElementById("times");
        var p = document.createElement("p");
        p.style.fontSize = "20px";
        p.style.backgroundColor = "rgba(74, 74, 196, 0.651)";
        p.style.borderRadius = "15px";
        p.style.padding = "15px";
        p.style.border = "3px solid black";
        p.textContent = "У вас нет активных записей";
        timesDiv.appendChild(p);
    }
}

  async function delete_appointment_from_current(id) {
    return fetch('http://localhost:8080/delete_appointment_from_current?appointmentId=' + id, {method:'POST'})
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch appointments');
            }
            return true;
        })
        .catch(error => {
           return false;
        });
}
  // Генерируем список времен
  generateList();


