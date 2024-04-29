  function formatDate(date) {
    const options = { month: 'long', day: 'numeric' };
    return date.toLocaleDateString('ru', options);
  }

  function getAllAppointments() {
    return fetch('http://localhost:8080/get_all_appointments')
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


async function generateTimeSlotsForDay(date) {
    return getAllAppointments()
        .then(appointments => {
            const timeSlotsForDay = appointments.map(appointment => {
                const appointmentDate = new Date(appointment.date);
                const hours = appointmentDate.getHours().toString().padStart(2, '0');
                const minutes = appointmentDate.getMinutes().toString().padStart(2, '0');
                const formattedDate = formatDate(appointmentDate); // Форматированная дата
                return { 
                    id: appointment.id, 
                    time: `${hours}:${minutes}`, 
                    isBooked: appointment.isBooked, 
                    date: formattedDate // Добавляем форматированную дату
                };
            });
            return timeSlotsForDay;
        })
        .catch(error => {
            console.error('Error generating time slots:', error);
            return []; // Возвращаем пустой массив в случае ошибки
        });
}
  
  // Генерируем и выводим временные интервалы
  async function generateTimeSlots() {
    const table = document.getElementById('timeSlotsTable');
    const timeSlotsBody = document.getElementById('timeSlotsBody');
    const datesRow = document.getElementById('datesRow');

    // Очистка таблицы перед добавлением новых данных
    timeSlotsBody.innerHTML = '';
    datesRow.innerHTML = ''; // Очищаем заголовки перед добавлением новых

    let currentDate = new Date();
    for (let i = 0; i < 7; i++) { // 7 заголовков для каждого дня недели
        const dateCell = document.createElement('th');
        const options = { weekday: 'short', day: 'numeric', month: 'short' };
        dateCell.textContent = currentDate.toLocaleDateString('ru', options);
        datesRow.appendChild(dateCell);
        currentDate.setDate(currentDate.getDate() + 1); // Переходим к следующему дню
    }

    currentDate = new Date(); // Сбросим дату на текущую дату для генерации временных слотов
    const timeSlotsForDay = await generateTimeSlotsForDay(currentDate);
    console.log(timeSlotsForDay);
    for (let i = 0; i < 12; i++) { // 12 временных слотов
        const timeRow = document.createElement('tr');
        for (let j = 0; j < 7; j++) { // Добавление для каждого дня
            const timeCell = document.createElement('td');
            const id_slot = timeSlotsForDay[j * 12 + i];
            timeCell.textContent = id_slot ? id_slot.time : '';
            timeCell.setAttribute('id', id_slot ? id_slot.id : '');

            if (id_slot && id_slot.isBooked === false) {
                timeCell.style.backgroundColor = 'green';
                timeCell.addEventListener('click', function() {
                    showNotification(id_slot.id, id_slot.date ,id_slot.time);
                });
            } else {
                timeCell.style.backgroundColor = 'red';
            }

            timeRow.appendChild(timeCell);
            currentDate.setDate(currentDate.getDate() + 1); // Переходим к следующему дню
        }
        timeSlotsBody.appendChild(timeRow);
        currentDate.setHours(0, 0, 0, 0); // Сбрасываем время на начало дня
    }
}

async function getCurrentUser() {
    return fetch('http://localhost:8080/get_current_user', {method:'GET'})
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


function connectUser(appointmentId) {
    return fetch('http://localhost:8080/connect_appointment_to_user?appointmentId=' + appointmentId.toString(), {method:'PUT'})
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch appointments');
            }
        })
        .catch(error => {
            console.error('Error fetching appointments:', error);
            return []; // Возвращаем пустой массив в случае ошибки
        });
}

async function showNotification(appointmentId, appointmentDate, appointmentTime) {
    var check = await getCurrentUser();
    
    console.log(check);
    if (check){
        const confirmation = confirm(`Вы уверены что хотите записаться на ${appointmentDate} ${appointmentTime}?`);
        if (confirmation) {
            connectUser(appointmentId);
            setTimeout(function(){
                location.reload();
            }, 1500);
        }
    }
    else{
        alert(`Вы не авторизованы`);
    }
    
}






generateTimeSlots();