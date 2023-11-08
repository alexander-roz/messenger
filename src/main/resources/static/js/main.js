/* Задание 3. Реализовать чат на основе эхо-сервера wss://echo-ws-service.herokuapp.com. Интерфейс состоит из input, куда вводится текст сообщения, и кнопки «Отправить». 
При клике на кнопку «Отправить» сообщение должно появляться в окне переписки. Эхо-сервер будет отвечать вам тем же сообщением, его также необходимо выводить в чат. 
Добавить в чат механизм отправки гео-локации. При клике на кнопку «Гео-локация» необходимо отправить данные серверу и в чат вывести ссылку на 
https://www.openstreetmap.org/ с вашей гео-локацией. Сообщение, которое отправит обратно эхо-сервер, не выводить. */

const url = "wss://echo-ws-service.herokuapp.com";

const text_message = document.getElementById('text_message');
const send_btn = document.getElementById('send_btn');
const geo_loc = document.getElementById('geo_loc');
const chat_window = document.getElementById('chat_window');
const chat_messages = document.getElementById('chat_messages');

//Соединение:
let websocket;

websocket = new WebSocket(url);
websocket.onopen = function (evt) {
  console.log("CONNECTED");
};
websocket.onclose = function (evt) {
  console.log("DISCONNECTED");
};
websocket.onmessage = function (evt) {
  writeToScreen('<span style="color: blue;">Сообщение от сервера: ' + evt.data + '</span>', 'flex-start')
};
websocket.onerror = function (evt) {
  writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data, 'flex-start')
};

//Отправка сообщения:
send_btn.addEventListener('click', () => {
  const message = text_message.value;
  writeToScreen("Сообщение отправителя: " + message);
  websocket.send(message);
});


//Вывод сообщения:
function writeToScreen(message, flex_position = 'flex-end') {
  let p = `<p class='messages' style='align-self: ${flex_position}'>${message}</p>`;
  chat_messages.innerHTML += p;
  chat_window.scrollTop = chat_window.scrollHeight;
}



//Гео-локация:
// Функция, выводящая текст об ошибке:
const error = () => {
  let text_error = 'Невозможно получить ваше местоположение';
  writeToScreen(text_error);
}

//Функция, срабатывающая при успешном получении геолокации:
const success = (position) => {
  const latitude = position.coords.latitude;
  const longitude = position.coords.longitude;
  const link_geo = `https://www.openstreetmap.org/#map=18/${latitude}/${longitude}`;
  writeToScreen(`<a  href='${link_geo}' target='_blank'>Моя гео-локация</a>`);
}

geo_loc.addEventListener('click', () => {
  if (!navigator.geolocation) {
    writeToScreen('Geolocation не поддерживается вашим браузером');
  } else {
    navigator.geolocation.getCurrentPosition(success, error);
  }
});

