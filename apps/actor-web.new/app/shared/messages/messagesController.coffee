class MessagesController
  list: null

  constructor: (@$scope, @$rootScope, @$timeout, @actorService) ->
    console.log '[AW]MessagesController constructor'
    @$rootScope.$on 'openConversation', (event, peer) =>
      console.log '[AW]MessagesController constructor: openConversation fired.'
      @getMessages(peer)

  getMessages: (peer) ->
    console.log '[AW]MessagesController getMessages'
    console.log '[AW]MessagesController getMessages: peer:', peer
    @actorService.bindChat peer, @renderMessages

  renderMessages: (messages) =>
    console.log '[AW]MessagesController renderMessages'
    console.log '[AW]MessagesController renderMessages: messages:', messages
    @$timeout =>
      @$scope.$apply (@scope) =>
        @list = messages
        @$rootScope.$broadcast 'renderMessages'

MessagesController.$inject = ['$scope', '$rootScope', '$timeout', 'actorService']

angular
  .module 'actorWeb'
  .controller 'messagesController', MessagesController
