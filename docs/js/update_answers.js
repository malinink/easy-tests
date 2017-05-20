/**
 * @author rezenbekk
 */

$(document).ready(function(){
    // Add a row
    $("#addAnswerRow").click(function() {
        var questionTypeId = Number($("#questionTypeId").text());
        var questionId = Number($("#questionId").text());

        var currentListSizeElem = $("#answerDtoListSize");
        var currentListSize = Number(currentListSizeElem.text());

        var newListSize = currentListSize + 1;
        currentListSizeElem.text(newListSize);

        var rowNumber = newListSize - 1;


        var answerTextId = answerTextIdCreate(rowNumber);
        var answerRowId = answerRowIdCreate(rowNumber);
        var radioButtonId = radioButtonIdCreate(rowNumber);
        var checkBoxId = checkBoxIdCreate(rowNumber);
        var serialNumberId = serialNumberIdCreate(rowNumber);

        var idName = 'answersList['+rowNumber+'].id';
        var answerTextName = 'answersList['+rowNumber+'].txt';
        var rightName = 'answersList['+rowNumber+'].right';
        var serialNumberName = 'answersList['+rowNumber+'].serialNumber';
        var questionIdName = 'answersList['+rowNumber+'].questionId';
        var conditionalInput = '';

        switch (questionTypeId) {
            // Один ответ
            case 1:
                conditionalInput = '<input type="checkbox" name="'+rightName+'" class="with-gap" id="' +radioButtonId+ '">'
                                + '<label for="'+radioButtonId+ '">'
                                + ' </label>' //Пробел поставлен специально, иначе радиокнопка не отрисовывается
                                + '<input name="'+idName+'" type="hidden" value="">'
                                + '<input name="'+serialNumberName+'" type="hidden" value="10">';
                break;
            // Много ответов
            case 2:
                conditionalInput = '<input type="checkbox" name="'+rightName+'" id="' +checkBoxId+ '">'
                    + '<label for="'+checkBoxId+ '">'
                    + ' </label>' //Пробел поставлен специально, иначе чекбокс не отрисовывается
                    + '<input name="'+idName+'" type="hidden" value="">'
                    + '<input name="'+serialNumberName+'" type="hidden" value="10">';
                break;
            // Нумерация
            case 3:
                conditionalInput = '<div class="input-field col s1">'
                    + '<input type="text" name="'+serialNumberName+'" id="' +serialNumberId+ '">'
                    + '<label for="'+serialNumberId+ '">'
                    + ' </label>'
                    + '<input name="'+idName+'" type="hidden" value="">'
                    + '<input name="'+rightName+'" type="hidden" value="">'
                    + '</div>';
                break;
            // Текст
            case 4:
                conditionalInput = '<input name="'+rightName+'" type="hidden" value="">'
                    + '<input name="'+idName+'" type="hidden" value="">'
                    + '<input name="'+serialNumberName+'" type="hidden" value="10">';
                break;
            default:
                console.log("No such type question: " + questionTypeId);
                break;
        }

        var newRow = '<div class="answerRow row" id="'+answerRowId+'">'
                    + '<div class="input-field col s4">'
                    + '<input id="'+answerTextId+'" name="'+answerTextName+'" type="text">'
                    + '<label class="active" for="'+answerTextId+ '">'
                    + 'New answer'
                    + '</label></div>'
                    + conditionalInput
                    + '<a class="deleteAnswerRow btn-floating btn-small waves-effect waves-light right grey darken-1">'
                    + '<i class="material-icons">delete</i></a>'
                    + '<div hidden id="questionIdHidden" >'
                    + '<input name="'+questionIdName+'" type="hidden" value="'+questionId+'">'
                    + '</div></div>';

        $("#answersGrid").append(newRow);
    });
    // Delete a row
    $("#answersGrid").on("click", "a.deleteAnswerRow", function() {
        var questionTypeId = Number($("#questionTypeId").text());

        var formRow = $(this).parents(".answerRow");
        var currentSelectId = formRow.attr("id");
        var currentIndex = extractIndex(currentSelectId);
        var formRowsAfter = formRow.nextAll().filter(".answerRow");

        console.log(formRowsAfter);

        var index = currentIndex;
        formRowsAfter.each(function () {

            // answerRow
            $(this).attr("id", answerRowIdCreate(index));

            var answerTextIdCurrent = $(this).find("input[type = 'text']").eq(0);
            answerTextIdCurrent.attr("id", answerTextIdCreate(index));

            switch (questionTypeId) {
                // Один ответ
                case 1:
                    var radioButtonIdCurrent = $(this).find("input[type = 'radio']");
                    radioButtonIdCurrent.attr("id", radioButtonIdCreate(index));
                    break;
                // Много ответов
                case 2:
                    var checkBoxIdCurrent = $(this).find("input[type = 'checkbox']");
                    checkBoxIdCurrent.attr("id", checkBoxIdCreate(index));
                    break;
                // Нумерация
                case 3:
                    var serialNumberIdCurrent = $(this).find("input[type = 'text']").eq(1);
                    serialNumberIdCurrent.attr("id", serialNumberIdCreate(index));
                    break;
                // Текст
                case 4:
                    break;
                default:
                    break;
            }

            index = index + 1;
        });
        formRow.remove();
    });
});

function answerTextIdCreate(index) {
    return "answerText_" + index;
}
function radioButtonIdCreate(index) {
    return "radioButton_" + index;
}
function checkBoxIdCreate(index) {
    return "checkBox_" + index;
}
function serialNumberIdCreate(index) {
    return "serialNumber_" + index;
}
function answerRowIdCreate(index) {
    return "answerRow_" + index;
}

function extractIndex(id) {
    return Number(id.match(/\d+/));
}