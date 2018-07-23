// Action types
const FETCH_DATA = 'FETCH_DATA';

// Action creator
export function fetchData(id) {
  return {
    type: FETCH_DATA,
    id,
  }
}
