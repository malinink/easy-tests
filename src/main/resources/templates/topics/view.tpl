layout 'layout/main.tpl', title:  'Topic',
content: contents {
h4 ('Topic')
form (class:'col s12') {
div (class:'row') {
div (class:'input-field col s12') {
textarea (id:'topic_name', class:'materialize-textarea black-text', readonly: 'readonly') {
yield topic.name
}
label (for:'topic_name', class: 'grey-text', 'Topic')
}
}
}
p {
button (href: '#', class: ' light-blue lighten-2 waves-effect waves-light btn') {
yield 'Questions'
}
}
a (class:'waves-effect waves-light btn-large red', href:'/personal/subjects/' + subjectId +'/topics/') {
i (class:'material-icons left', 'cancel')
yield 'Back'
}
}
