import { fetchData } from './action.js';
const SockJS = require('sockjs-client');
const Stomp = require('stompjs');

const socket = SockJS('http://localhost:8080/threats');
const stompClient = Stomp.over(socket);

export function register(registrations) {
	stompClient.connect({}, function(frame) {
		registrations.forEach(function (registration) {
			stompClient.subscribe(registration.route, registration.callback);
		});
	});
}

export const init = (store) => {
	register([
		{ route: '/topic/data', callback: (message) => { store.dispatch(fetchData(JSON.parse(message.body))) } }
	]);
};
