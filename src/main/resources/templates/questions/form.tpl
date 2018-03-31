layout 'layout/main.tpl', title:  'Question',
content: contents {
if (update) {
h4 ('Update')
} else if (create) {
h4 ('Create')
}
form (class:'col s12', method:'post') {
input (type:'hidden', name:_csrf.parameterName, value:_csrf.token)
div (class:'row') {
div (class:'input-field col s12') {
if (errors && errors.hasFieldErrors('text')) {
textarea (id:'question_text', name: 'text', class:'materialize-textarea validate invalid', value:errors.getFieldValue('text')) {}
label (for:'question_text', 'data-error':errors.getFieldErrors('text')*.getDefaultMessage().join(', '), 'text')
} else {
textarea (id:'question_text', name: 'text', class:'materialize-textarea') {
if (update)
{
yield question.text
}
}
label (for:'question_text', class: 'grey-text', 'Question')
}
}
}
div (class:'row') {
div (class:'col s12') {
h6 ('Question type', class: 'grey-text')
if (errors && errors.hasFieldErrors('questionTypeId')) {
p {
h6 (errors.getFieldErrors('questionTypeId')*.getDefaultMessage())
}
}
def id = 0
questionTypes.each { questionType ->
id++
if (question.questionTypeId == id)
{
p {
input (id: id, type: 'radio', name: 'questionTypeId', checked: 'checked', value: id)
label (for: id, questionType.name)
}
}
else
{
p {
if (create && id == 1)
{
input (id: id, type: 'radio', name: 'questionTypeId', checked: 'checked', value: id)
label (for: id, questionType.name)
}
else
{
input (id: id, type: 'radio', name: 'questionTypeId', value: id)
label (for: id, questionType.name)
}
}
}
}
}
}
div (class:'row') {
div (class:'col s12') {
button (class:'waves-effect waves-light btn-large blue', 'type':'submit') {
i (class:'material-icons left', 'save')
if (update) {
yield 'Update'
} else if (create) {
yield 'Create'
}
}
a (class:'waves-effect waves-light btn-large red', href:'/personal/topics/' + topicId +'/questions/') {
i (class:'material-icons left', 'cancel')
yield 'Cancel'
}
}
}
}
}
