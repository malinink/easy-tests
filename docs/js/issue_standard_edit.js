/**
 * @author SingularityA
 */
// TODO: refactor later (all functions)
/**
 * updates selects after loading page
 */
$(document).ready(function() {
    var selects = $("#topicPrioritiesBlock").find("select");
    var selectsInfo = $("#topicSelects").find(".topicSelectRow");

    selects.each(function () {
        var thisSelectId = $(this).attr("id");
        var thisSelectInfo = selectsInfo.find("p.selectId").filter(function () {
            return $(this).text() == thisSelectId;
        }).parents(".topicSelectRow");
        var thisSelectValue = thisSelectInfo.find("p.topicId").text();

        var otherSelects = $(this).parents(".topicPriorityForm").siblings().find("select");
        otherSelects.find("option[value='" + thisSelectValue + "']").attr("disabled", true);
    });

    selects = $("#questionTypeOptionsBlock").find("select");
    selectsInfo = $("#questionTypeSelects").find(".questionTypeSelectRow");

    selects.each(function () {
        var thisSelectId = $(this).attr("id");
        var thisSelectInfo = selectsInfo.find("p.selectId").filter(function () {
            return $(this).text() == thisSelectId;
        }).parents(".questionTypeSelectRow");
        var thisSelectValue = thisSelectInfo.find("p.questionTypeId").text();

        var otherSelects = $(this).parents(".questionTypeOptionForm").siblings().find("select");
        otherSelects.find("option[value='" + thisSelectValue + "']").attr("disabled", true);
    });

    $('select').material_select();
});

function tpFieldName(index, field) {
    return "topicPriorities[" + index + "]." + field;
}

function tpFieldId(index, field) {
    return "topicPriorities_" + field + "_" + index;
}

function qtoFieldName(index, field) {
    return "questionTypeOptions[" + index + "]." + field;
}

function qtoFieldId(index, field) {
    return "questionTypeOptions_" + field + "_" + index;
}

function cutIndex(fieldName) {
    return Number(fieldName.match(/\d+/));
}

/**
 * adds another row with TopicPriority form for on button 'plus' click
 */
$(document).ready(function() {
    $("#addTopicPriorityForm").click(function() {
        var currentListSizeElem = $("#topicsListTakenSize");
        var totalListSize = Number($("#topicsListTotalSize").text());
        var currentListSize = Number(currentListSizeElem.text());
        var newListSize = currentListSize + 1;

        // кнопка "добавить" недоступна, если количество строк равно количеству существующих тем
        currentListSizeElem.text(newListSize);
        if (newListSize >= totalListSize) {
            $("#addTopicPriorityForm").attr("disabled", true);
        }

        var optionsString = '<option value="" disabled selected>Choose Topic Name</option>';
        var createdTopicRows = $("#topicSelects").find(".topicSelectRow");

        // формирование пунктов селекта
        $(".topicsListRow").each(function() {
            var id = $(this).find(".id").text();
            var name = $(this).find(".name").text();
            var topic = createdTopicRows.find("p.topicId").filter(function () {
                return $(this).text() === id;
            });

            if (topic.length != 0) {
                optionsString += '<option value="' + id + '" disabled>' + name + '</option>';
            } else {
                optionsString += '<option value="' + id + '">' + name + '</option>';
            }
        });

        var rowIndex = newListSize - 1;

        var tpIdId = tpFieldId(rowIndex, "id");
        var tpTopicIdId = tpFieldId(rowIndex, "topicId");
        var tpIsPreferableId = tpFieldId(rowIndex, "isPreferable");

        var tpIdName = tpFieldName(rowIndex, "id");
        var tpTopicIdName = tpFieldName(rowIndex, "topicId");
        var tpIsPreferableName = tpFieldName(rowIndex, "isPreferable")

        var newRow =
            '<div class="topicPriorityForm row">'
            +  '<input id="' + tpIdId + '" name="' + tpIdName + '" disabled value="" type="hidden">'
            +  '<div class="input-field col s6">'
            +    '<select id="' + tpTopicIdId + '" name="' + tpTopicIdName + '">'
            +       optionsString
            +    '</select>'
            +    '<label>Topic</label>'
            +  '</div>'
            +  '<div class="col s4">'
            +    '<p>'
            +        '<input id="' + tpIsPreferableId + '" name="' + tpIsPreferableName + '" type="checkbox"/>'
            +        '<label for="' + tpIsPreferableId + '">Is Preferable</label>'
            +    '</p>'
            +  '</div>'
            +  '<a class="deleteTopicPriorityForm btn-floating btn-large waves-effect waves-light right blue-grey">'
            +    '<i class="material-icons">delete</i>'
            +  '</a>'
            +'</div>';
        // добавление новой строки-формы
        $("#topicPrioritiesBlock").append(newRow);
        $('select').material_select();

        var hiddenRow =
            '<div class="topicSelectRow">'
            +	'<p class="selectId">' + tpTopicIdId + '</p>'
            +	'<p class="topicId"></p>'
            +'</div>';
        // добавление строки в hidden слой
        $("#topicSelects").append(hiddenRow);
    });
});

/**
 * updates free and busy topics in selects lists on change in select
 */
$(document).ready(function() {
    $("#topicPrioritiesBlock").on("change", "select", function() {
        var selectId = $(this).attr("id");
        var selectInfo = $("#topicSelects").find(".topicSelectRow").find("p.selectId").filter(function () {
            return $(this).text() === selectId;
        }).parents(".topicSelectRow");

        var changedSelectValue = selectInfo.find("p.topicId").text();
        var newSelectValue = $(this).val();
        if (changedSelectValue === newSelectValue)
            return;

        // поправка в hidden слое
        selectInfo.find(".topicId").text(newSelectValue);

        // обновление списков в селектах
        var otherSelects = $(this).parents(".topicPriorityForm").siblings().find("select");
        if (changedSelectValue != null && changedSelectValue != "") {
            otherSelects.find("option[value = '" + changedSelectValue + "']").attr("disabled", false);
        }
        otherSelects.find("option[value = '" + newSelectValue + "']").attr("disabled", true);
        otherSelects.material_select();
    });
});

/**
 * deletes TopicPriority form on button 'delete' click
 */
$(document).ready(function() {
    $("#topicPrioritiesBlock").on("click", "a.deleteTopicPriorityForm", function() {
        // поправка количества занятых тем
        var currentListSizeElem = $("#topicsListTakenSize");
        var newListSize = Number(currentListSizeElem.text()) - 1;
        currentListSizeElem.text(newListSize);

        var formRow = $(this).parents(".topicPriorityForm");
        var currentSelectId = formRow.find("select").attr("id");
        var currentSelectValue = formRow.find("select").val();

        // отрезание индекса
        var currentIndex = cutIndex(currentSelectId);
        // все элементы после удаляемого
        var formRowsAfter = formRow.nextAll().filter(".topicPriorityForm");
        // скрытый слой селектов
        var selectsInfo = $("#topicSelects").find(".topicSelectRow");

        // удаление строки из hidden слоя
        selectsInfo.find("p.selectId").filter(function () {
            return $(this).text() === currentSelectId;
        }).parents(".topicSelectRow").remove();

        // поправка id строк-форм
        var index = currentIndex;
        formRowsAfter.each(function () {
            var rowId = $(this).find("input[type='hidden']");
            var rowSelect = $(this).find("select");
            var rowCheckbox = $(this).find(":checkbox");

            // для скрытого id
            rowId.attr("id", tpFieldId(index, "id"));
            rowId.attr("name", tpFieldName(index, "id"));

            // для селектов
            var rowSelectId = rowSelect.attr("id");
            var newSelectId = tpFieldId(index, "topicId");

            rowSelect.attr("id", newSelectId);
            rowSelect.attr("name", tpFieldName(index, "topicId"));

            selectsInfo.find("p.selectId").filter(function () {
                return $(this).text() === rowSelectId;
            }).text(newSelectId);

            // для чекбоксов
            var newCheckboxId = tpFieldId(index, "isPreferable");
            rowCheckbox.attr("id", newCheckboxId);
            rowCheckbox.attr("name", tpFieldName(index, "isPreferable"));
            rowCheckbox.next("label").attr("for", newCheckboxId);
            index++;
        });

        // удаление строки-формы
        formRow.remove();

        // обновление селектов (освобождение темы удаляемой строки)
        if (currentSelectValue != null && currentSelectValue != "") {
            var selects = $("#topicPrioritiesBlock").find("select");
            selects.find("option[value = '" + currentSelectValue + "']").attr("disabled", false);
            selects.material_select();
        }

        // кнопка "добавить" снова активна
        $("#addTopicPriorityForm").attr("disabled", false);
    });
});

/**
 * adds another row with QuestionTypeOption form for on button 'plus' click
 */
$(document).ready(function() {
    $("#addQuestionTypeOptionForm").click(function() {
        var currentListSizeElem = $("#questionTypesListTakenSize");
        var totalListSize = Number($("#questionTypesListTotalSize").text());
        var currentListSize = Number(currentListSizeElem.text());
        var newListSize = currentListSize + 1;

        // кнопка "добавить" недоступна, если количество строк равно количеству существующих тем
        currentListSizeElem.text(newListSize);
        if (newListSize >= totalListSize) {
            $("#addQuestionTypeOptionForm").attr("disabled", true);
        }

        var optionsString = '<option value="" disabled selected>Choose Question Type</option>';
        var createdQuestionTypeRows = $("#questionTypeSelects").find(".questionTypeSelectRow");

        // формирование пунктов селекта
        $(".questionTypesListRow").each(function() {
            var id = $(this).find(".id").text();
            var name = $(this).find(".name").text();
            var questionType = createdQuestionTypeRows.find("p.questionTypeId").filter(function () {
                return $(this).text() === id;
            });

            if (questionType.length != 0) {
                optionsString += '<option value="' + id + '" disabled>' + name + '</option>';
            } else {
                optionsString += '<option value="' + id + '">' + name + '</option>';
            }
        });

        var rowIndex = newListSize - 1;

        var qtoIdId = qtoFieldId(rowIndex, "id");
        var qtoQuestionTypeIdId = qtoFieldId(rowIndex, "questionTypeId");
        var qtoMinQuestionsId = qtoFieldId(rowIndex, "minQuestions");
        var qtoMaxQuestionsId = qtoFieldId(rowIndex, "maxQuestions");
        var qtoTimeLimitId = qtoFieldId(rowIndex, "timeLimit");

        var qtoIdName = qtoFieldName(rowIndex, "id");
        var qtoQuestionTypeIdName = qtoFieldName(rowIndex, "questionTypeId");
        var qtoMinQuestionsName = qtoFieldName(rowIndex, "minQuestions");
        var qtoMaxQuestionsName = qtoFieldName(rowIndex, "maxQuestions");
        var qtoTimeLimitName = qtoFieldName(rowIndex, "timeLimit");

        var newRow =
            '<div class="questionTypeOptionForm row">'
            + '<input id="' + qtoIdId + '" name="' + qtoIdName + '" disabled value="" type="hidden">'
            + '<div class="input-field col s5">'
            +   '<select id="' + qtoQuestionTypeIdId + '" name="' + qtoQuestionTypeIdName + '">'
            +      optionsString
            +   '</select>'
            +   '<label>Question Type</label>'
            + '</div>'
            + '<div class="input-field col s2">'
            +    '<input id="' + qtoMinQuestionsId + '" name="' + qtoMinQuestionsName + '" placeholder="No Restriction" type="number" min="1" class="validate">'
            +    '<label class="active" for="' + qtoMinQuestionsId + '">Minimal number of questions</label>'
            + '</div>'
            + '<div class="input-field col s2">'
            +    '<input id="' + qtoMaxQuestionsId + '" name="' + qtoMaxQuestionsName + '" placeholder="No Restriction" type="number" min="1" class="validate">'
            +    '<label class="active" for="' + qtoMaxQuestionsId + '">Maximal number of questions</label>'
            + '</div>'
            + '<div class="input-field col s2">'
            +    '<input id="' + qtoTimeLimitId + '" name="' + qtoTimeLimitName + '" placeholder="No Restriction" type="number" min="1" class="validate">'
            +    '<label class="active" for="' + qtoTimeLimitId + '">Time limit for type</label>'
            + '</div>'
            + '<a class="deleteQuestionTypeOptionForm btn-floating btn-large waves-effect waves-light right blue-grey"><i class="material-icons">delete</i></a>'
            +'</div>';

        // добавление новой строки-формы
        $("#questionTypeOptionsBlock").append(newRow);
        $('select').material_select();

        var hiddenRow =
            '<div class="questionTypeSelectRow">'
            +	'<p class="selectId">' + qtoQuestionTypeIdId + '</p>'
            +	'<p class="questionTypeId"></p>'
            +'</div>';
        // добавление строки в hidden слой
        $("#questionTypeSelects").append(hiddenRow);
    });
});

/**
 * updates free and busy questions types in selects lists on change in select
 */
$(document).ready(function() {
    $("#questionTypeOptionsBlock").on("change", "select", function() {
        var selectId = $(this).attr("id");
        var selectInfo = $("#questionTypeSelects").find(".questionTypeSelectRow").find("p.selectId").filter(function () {
            return $(this).text() === selectId;
        }).parents(".questionTypeSelectRow");

        var changedSelectValue = selectInfo.find("p.questionTypeId").text();
        var newSelectValue = $(this).val();
        if (changedSelectValue === newSelectValue)
            return;

        // поправка в hidden слое
        selectInfo.find(".questionTypeId").text(newSelectValue);

        // обновление списков в селектах
        var otherSelects = $(this).parents(".questionTypeOptionForm").siblings().find("select");
        if (changedSelectValue != null && changedSelectValue != "") {
            otherSelects.find("option[value = '" + changedSelectValue + "']").attr("disabled", false);
        }
        otherSelects.find("option[value = '" + newSelectValue + "']").attr("disabled", true);
        otherSelects.material_select();
    });
});

/**
 * deletes QuestionTypeOption form on button 'delete' click
 */
$(document).ready(function() {
    $("#questionTypeOptionsBlock").on("click", "a.deleteQuestionTypeOptionForm", function() {
        // поправка количества занятых тем
        var currentListSizeElem = $("#questionTypesListTakenSize");
        var newListSize = Number(currentListSizeElem.text()) - 1;
        currentListSizeElem.text(newListSize);

        var formRow = $(this).parents(".questionTypeOptionForm");
        var currentSelectId = formRow.find("select").attr("id");
        var currentSelectValue = formRow.find("select").val();

        // отрезание индекса
        var currentIndex = cutIndex(currentSelectId);
        // все элементы после удаляемого
        var formRowsAfter = formRow.nextAll().filter(".questionTypeOptionForm");
        // скрытый слой селектов
        var selectsInfo = $("#questionTypeSelects").find(".questionTypeSelectRow");

        // удаление строки из hidden слоя
        selectsInfo.find("p.selectId").filter(function () {
            return $(this).text() === currentSelectId;
        }).parents(".questionTypeSelectRow").remove();

        // поправка id строк-форм
        var index = currentIndex;
        formRowsAfter.each(function () {
            var rowId = $(this).find("input[type='hidden']");
            var rowSelect = $(this).find("select");
            var rowMin = $(this).find("input[type='number']").eq(0);
            var rowMax = $(this).find("input[type='number']").eq(1);
            var rowTimeLimit = $(this).find("input[type='number']").eq(2);

            // для скрытого id
            rowId.attr("id", qtoFieldId(index, "id"));
            rowId.attr("name", qtoFieldName(index, "id"));

            // для селектов
            var rowSelectId = rowSelect.attr("id");
            var newSelectId = qtoFieldName(index, "questionTypeId");

            rowSelect.attr("id", newSelectId);
            rowSelect.attr("name", qtoFieldName(index, "questionTypeId"));

            selectsInfo.find("p.selectId").filter(function () {
                return $(this).text() === rowSelectId;
            }).text(newSelectId);

            // для поля min
            var newMinId = qtoFieldId(index, "minQuestions");
            rowMin.attr("id", newMinId);
            rowMin.attr("name", qtoFieldName(index, "minQuestions"));
            rowMin.next("label").attr("for", newMinId);

            // для поля max
            var newMaxId = qtoFieldId(index, "maxQuestions");
            rowMax.attr("id", newMaxId);
            rowMax.attr("name", qtoFieldName(index, "maxQuestions"));
            rowMax.next("label").attr("for", newMaxId);

            // для поля timeLimit
            var newTimeLimitId = qtoFieldId(index, "timeLimit");
            rowTimeLimit.attr("id", newTimeLimitId);
            rowTimeLimit.attr("name", qtoFieldName(index, "timeLimit"));
            rowTimeLimit.next("label").attr("for", newTimeLimitId);

            index++;
        });

        // удаление строки-формы
        formRow.remove();

        // обновление селектов (освобождение темы удаляемой строки)
        if (currentSelectValue != null && currentSelectValue != "") {
            var selects = $("#questionTypeOptionsBlock").find("select");
            selects.find("option[value = '" + currentSelectValue + "']").attr("disabled", false);
            selects.material_select();
        }

        // кнопка "добавить" снова активна
        $("#addQuestionTypeOptionForm").attr("disabled", false);
    });
});
