import { FETCH_DATA } from './action';

const initialState = {
  threats: [],
}

export function rootReducer(state = initialState, action) {
  switch(action.type) {
    case 'FETCH_DATA':
      return Object.assign(...state, { threats: action.id });
    default:
      return state
  }
}
