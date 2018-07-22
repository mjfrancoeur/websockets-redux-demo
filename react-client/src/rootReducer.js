const initialState = {
  newestEmployeeId: null,
}

export function rootReducer(state = initialState, action) {
  switch(action.type) {
    case 'ADD_EMPLOYEE':
      return Object.assign(...state, { newestEmployeeId: action.id });
    default:
      return state
  }
}
