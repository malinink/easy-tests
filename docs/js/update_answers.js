/**
 * @author rezenbekk
 */

$(document).ready(function () {
    // Add a row
    $("#addAnswerRow").click(function () {
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

        var idName = 'answersList[' + rowNumber + '].id';
        var answerTextName = 'answersList[' + rowNumber + '].txt';
        var rightName = 'answersList[' + rowNumber + '].right';
        var serialNumberName = 'answersList[' + rowNumber + '].serialNumber';
        var questionIdName = 'answersList[' + rowNumber + '].questionId';
        var conditionalInput = '';

        switch (questionTypeId) {
            // Один ответ
            case 1:
                conditionalInput = '<input type="checkbox" name="' + rightName + '" class="with-gap" id="' + radioButtonId + '">'
                    + '<label for="' + radioButtonId + '">'
                    + ' </label>' //Пробел поставлен специально, иначе радиокнопка не отрисовывается
                    + '<input name="' + idName + '" type="hidden" value="">'
                    + '<input name="' + serialNumberName + '" type="hidden" value="10">';
                break;
            // Много ответов
            case 2:
                conditionalInput = '<input type="checkbox" name="' + rightName + '" id="' + checkBoxId + '">'
                    + '<label for="' + checkBoxId + '">'
                    + ' </label>' //Пробел поставлен специально, иначе чекбокс не отрисовывается
                    + '<input name="' + idName + '" type="hidden" value="">'
                    + '<input name="' + serialNumberName + '" type="hidden" value="10">';
                break;
            // Нумерация
            case 3:
                conditionalInput = '<div class="input-field col s1">'
                    + '<input type="text" name="' + serialNumberName + '" id="' + serialNumberId + '">'
                    + '<label for="' + serialNumberId + '">'
                    + ' </label>'
                    + '<input name="' + idName + '" type="hidden" value="">'
                    + '<input name="' + rightName + '" type="hidden" value="">'
                    + '</div>';
                break;
            // Текст
            case 4:
                conditionalInput = '<input name="' + rightName + '" type="hidden" value="">'
                    + '<input name="' + idName + '" type="hidden" value="">'
                    + '<input name="' + serialNumberName + '" type="hidden" value="10">';
                break;
            default:
                console.log("No such type question: " + questionTypeId);
                break;
        }

        var newRow = '<div class="answerRow row" id="' + answerRowId + '">'
            + '<div class="input-field col s4">'
            + '<input id="' + answerTextId + '" name="' + answerTextName + '" type="text">'
            + '<label class="active" for="' + answerTextId + '">'
            + 'New answer'
            + '</label></div>'
            + conditionalInput
            + '<a class="deleteAnswerRow btn-floating btn-small waves-effect waves-light right grey darken-1">'
            + '<i class="material-icons">delete</i></a>'
            + '<div hidden id="questionIdHidden" >'
            + '<input name="' + questionIdName + '" type="hidden" value="' + questionId + '">'
            + '</div></div>';

        $("#answersGrid").append(newRow);
    });
    // Delete a row
    $("#answersGrid").on("click", "a.deleteAnswerRow", function () {
        var questionTypeId = Number($("#questionTypeId").text());

        var formRow = $(this).parents(".answerRow");
        var currentSelectId = formRow.attr("id");
        var currentIndex = extractIndex(currentSelectId);
        var formRowsAfter = formRow.nextAll().filter(".answerRow");

        var currentListSizeElem = $("#answerDtoListSize");
        var currentListSize = Number(currentListSizeElem.text());
        currentListSizeElem.text(currentListSize - 1);

        var index = currentIndex;
        formRowsAfter.each(function () {

            id1 = index + 1;

            var idNameOld = 'answersList[' + id1 + '].id';
            var answerTextNameOld = 'answersList[' + id1 + '].txt';
            var rightNameOld = 'answersList[' + id1 + '].right';
            var serialNumberNameOld = 'answersList[' + id1 + '].serialNumber';
            var questionIdNameOld = 'answersList[' + id1 + '].questionId';

            var idName = 'answersList[' + index + '].id';
            var answerTextName = 'answersList[' + index + '].txt';
            var rightName = 'answersList[' + index + '].right';
            var serialNumberName = 'answersList[' + index + '].serialNumber';
            var questionIdName = 'answersList[' + index + '].questionId';

            // answerRow
            $(this).attr("id", answerRowIdCreate(index));
            var answerTextIdCurrent = $(this).find("input[name='" + answerTextNameOld + "']");
            answerTextIdCurrent.attr("id", answerTextIdCreate(index));
            answerTextIdCurrent.attr("name", answerTextName);

            switch (questionTypeId) {
                // Один ответ
                case 1:
                    var radioButtonIdCurrent = $(this).find("input[name='" + rightNameOld + "']");
                    radioButtonIdCurrent.attr("id", radioButtonIdCreate(index));
                    radioButtonIdCurrent.attr("name", rightName);
                    break;
                    var idCurrent = $(this).find("input[name='" + idNameOld + "']");
                    idCurrent.attr("name", idName);
                    break;
                    var serialNumberCurrent = $(this).find("input[name='" + serialNumberNameOld + "']");
                    serialNumberCurrent.attr("name", serialNumberName);
                    break;
                // Много ответов
                case 2:
                    var checkBoxIdCurrent = $(this).find("input[name='" + rightNameOld + "']");
                    checkBoxIdCurrent.attr("id", checkBoxIdCreate(index));
                    checkBoxIdCurrent.attr("name", rightName);
                    idCurrent = $(this).find("input[name='" + idNameOld + "']");
                    idCurrent.attr("name", idName);
                    break;
                    serialNumberCurrent = $(this).find("input[name='" + serialNumberNameOld + "']");
                    serialNumberCurrent.attr("name", serialNumberName);
                    break;
                // Нумерация
                case 3:
                    var serialNumberIdCurrent = $(this).find("input[name = '" + serialNumberNameOld + "']");
                    console.log(serialNumberIdCurrent);
                    serialNumberIdCurrent.attr("id", serialNumberIdCreate(index));
                    serialNumberIdCurrent.attr("name", serialNumberName);
                    idCurrent = $(this).find("input[name='" + idNameOld + "']");
                    idCurrent.attr("name", idName);
                    var rightCurrent = $(this).find("input[name='" + rightNameOld + "']");
                    rightCurrent.attr("name", rightName);
                    break;
                // Текст
                case 4:
                    idCurrent = $(this).find("input[name='" + idNameOld + "']");
                    idCurrent.attr("name", idName);
                    rightCurrent = $(this).find("input[name='" + rightNameOld + "']");
                    rightCurrent.attr("name", rightName);
                    serialNumberIdCurrent.attr("id", serialNumberIdCreate(index));
                    serialNumberIdCurrent.attr("name", serialNumberName);
                    break;
                default:
                    break;
            }
            var questionIdCurrent = $(this).find("input[name='" + questionIdNameOld + "']");
            questionIdCurrent.attr("name", questionIdName);

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