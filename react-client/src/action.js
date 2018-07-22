// Action types
const ADD_EMPLOYEE = 'ADD_EMPLOYEE';

// Action creator
export function addEmployee(id) {
  return {
    type: ADD_EMPLOYEE,
    id,
  }
}
