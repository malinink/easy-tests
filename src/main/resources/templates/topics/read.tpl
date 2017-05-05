layout 'layout/main.tpl', title:  'View topic',
content: contents {
  h4 ('View topic')
  div (class:'row') {
    div (class:'input-field col s12') {
      input (name:'name', id:'field_name', type:'text', class:'validate', readonly:'true', value:topic.name)
      label (for:'field_name', 'Topic name')
    }
  }
  div (class:'row') {
    div (class:'input-field col s12') {
      a (href: '/personal/issue_standard/' + issueStandardId) {yield 'View issue standard'}
    }
  }
  div (class:'row') {
    div (class:'col s12') {
      a (class:'waves-effect waves-light btn-large red', onclick: 'document.location.href="/personal/topics/list"') {
        i (class:'material-icons left', 'close')
        yield 'Close'
      }
    }
  }
}