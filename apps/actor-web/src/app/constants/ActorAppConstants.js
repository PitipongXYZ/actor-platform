var keymirror = require('keymirror');

module.exports = {
  PeerTypes: {
    USER: 'user',
    GROUP: 'group'
  },

  ActionTypes: keymirror({
    APP_HIDDEN: null,
    APP_VISIBLE: null,

    AUTH_SMS_REQUESTED: null,
    SET_LOGGED_IN: null,

    DIALOGS_CHANGED: null,
    SELECT_DIALOG_PEER: null,
    SELECTED_DIALOG_INFO_CHANGED: null,

    SEND_MESSAGE_TEXT: null,
    SEND_MESSAGE_FILE: null,
    SEND_MESSAGE_PHOTO: null,

    SHOW_ACTIVITY: null,
    HIDE_ACTIVITY: null,

    SHOW_MODAL: null,
    HIDE_MODAL: null,

    CONTACT_ADD: null,
    CONTACT_REMOVE: null,
    CONTACT_LIST: null
  }),

  ActivityTypes: keymirror({
    USER_PROFILE: null,
    GROUP_PROFILE: null
  })
};
