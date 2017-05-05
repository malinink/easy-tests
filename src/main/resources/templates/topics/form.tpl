layout 'layout/main.tpl', title: methodType=='create' ? 'Create topic' : 'Edit topic',
content: contents {
  h4 (methodType=='create' ? 'Create topic' : 'Edit topic')
  form (class:'col s12', method:'post') {
    input (type:'hidden', name:_csrf.parameterName, value:_csrf.token)
    div (class:'row') {
      div (class:'input-field col s12') {
        if (errors && errors.hasFieldErrors('name')) {
            input (name:'name', id:'field_name', type:'text', class:'validate invalid', value:errors.getFieldValue('name'))
            label (for:'field_name', 'data-error':errors.getFieldErrors('name')*.getDefaultMessage().join(', '), 'name')
        } else {
            input (name:'name', id:'field_name', type:'text', class:'validate', value:topic.name)
            label (for:'field_name', 'Topic name')
        }
      }
    }
    div (class:'row') {
      div (class:'col s12') {
        button ('class':'waves-effect waves-light btn-large blue', 'type':'submit', 'name':'save') {
          i (class:'material-icons left', 'save')
          yield 'Save'
        }
        a (class:'waves-effect waves-light btn-large red', onclick: 'document.location.href="/personal/topics/list"') {
          i (class:'material-icons left', 'cancel')
          yield 'Cancel'
        }
      }
    }
  }
}