function makeAction (type) {
  return ({ dispatch }, ...args) => dispatch(type, ...args)
};

const initData = {
  user_name: 'visitor',
  user_identify: 'visitor',

}

export const initStore = ({dispatch}) => {
  dispatch('INIT_STORE', initData)
}

export const handle_user_name = makeAction('HANDLE_USER_NAME')

export const handle_user_role = makeAction('HANDLE_USER_ROLE')


