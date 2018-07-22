import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';

import './index.css';
import App from './App';
import { rootReducer } from './rootReducer';
import { addEmployee } from './action';
import registerServiceWorker from './registerServiceWorker';
import { init } from './websocketListener.js';

const store = createStore(rootReducer, composeWithDevTools());
init(store);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);
registerServiceWorker();
