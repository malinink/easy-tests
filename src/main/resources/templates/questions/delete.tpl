layout 'layout/main.tpl', title:  'Delete confirmation',
content: contents {
  h4 ('Delete confirmation')
  form (class:'col s12', method:'post') {
    input (type:'hidden', name:_csrf.parameterName, value:_csrf.token)
    div (class:'row') {
       p (class: 'col s12') { yield 'Do you really want to delete this question?' }
    }
    div (class:'row') {
      div (class:'col s12') {
        button ('class':'waves-effect waves-light btn-large blue', 'type':'submit', 'name':'delete') {
          i (class:'material-icons left', 'delete')
          yield 'Delete'
        }
        a (class:'waves-effect waves-light btn-large red', href:'/personal/topics/' + topicId +'/questions/') {
          i (class:'material-icons left', 'cancel')
          yield 'Cancel'
        }
      }
    }
  }
}
