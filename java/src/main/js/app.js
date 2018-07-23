'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const when = require('when');
const client = require('./client');

const follow = require('./follow'); // function to hop multiple links by "rel"

const stompClient = require('./websocket-listener');

const root = '/api';

class App extends React.Component {

	constructor(props) {
		super(props);
		this.onSendData = this.onSendData.bind(this);
	}

	// tag::on-create[]
	onSendData(newEmployee = {}) {
		follow(client, root, ['employees']).done(response => {
			client({
				method: 'POST',
				path: response.entity._links.self.href,
				entity: newEmployee,
				headers: {'Content-Type': 'application/json'}
			})
		})
	}
	// end::on-create[]

	render() {
		return (
			<div>
				<button onClick={this.onSendData}>Send data</button>
			</div>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
