let userId = $.cookie('userId');
let categoryID = -1;

function showCategories() {
    $('#listCategories tbody').html('');
    $.ajax({
        type: "GET",
        url: `categories?userId=${userId}`,
        success: [function (result) {
            let categories = result.data;
            for (let i = 0; i < categories.length; i++) {
                let markup = "<tr>" +
                    "<td>" + categories[i].id + "</td>" +
                    "<td>" + categories[i].name + "</td>"
                    + `<td style="text-align: center"><a href="#" id="change_${categories[i].id}"><i class="fa fa-edit" style="font-size:20px"></i></a></td>`
                    + `<td style="text-align: center"><a href="#" id="delete_${categories[i].id}"><i class="fa fa-trash" style="font-size:20px"></i></a></td>`
                    + `<td style="text-align: center"><a href="#" id="shows_${categories[i].id}"><i class="fa fa-bath" style="font-size:20px"></i></a></td>`;
                $('#listCategories tbody').append(markup);
            }
            pagination();
        }],
        error: [function (e) {
            alert(e.responseJSON(e.message));
        }]
    });
}

function showCards(idCat) {
    $('#listCards tbody').html('');
    $.ajax({
        type: "GET",
        url: `cards?categoryId=${idCat}`,
        success: [function (result) {
            let cards = result.data;
            for (let i = 0; i < cards.length; i++) {
                let markup = "<tr>" +
                    "<td>" + cards[i].id + "</td>" +
                    "<td>" + cards[i].question + "</td>"
                    + "<td>" + cards[i].answer + "</td>"
                    + `<td style="text-align: center"><a href="#" id="changeCard_${cards[i].id}"><i class="fa fa-edit" style="font-size:20px"></i></a></td>`
                    + `<td style="text-align: center"><a href="#" id="deleteCard_${cards[i].id}"><i class="fa fa-trash" style="font-size:20px"></i></a></td>`;
                $('#listCards tbody').append(markup);
            }
            pagination();
        }],
        error: [function (e) {
            //console.error(e);
            alert(e.responseJSON.message);
        }]
    });
}

/**
 * -------------------------------------------------------Аутентификация--------------------------------------------
 */
function load() {
    {
        $.ajax({
            url: 'user',
            method: "GET",
            success: [function (result) {
                let html_table = "";
                let users = result.data;
                for (let i = 0; i < users.length; i++) {
                    let user = users[i];
                    html_table += "<tr>";
                    html_table += `<td><b>${user.id}</b></td>`;
                    html_table += `<td>${user.login}</td>`;
                    html_table += `<td><button class="btn btn-danger" id = "delete-${user.id}">Delete</button></td>`;
                    html_table += "</tr>";
                }
                $('#table-users').html(html_table);
            }],
            error: [function () {
                alert("error!!!")
            }]
        })
    }
}

$(document).ready(function () {

    load();
    $('#table-users').on('click', 'button', function () {
        let arr = $(this).attr('id').split('-');
        let id = arr[1];
        $.ajax({
            url: `user?id=${id}`,
            type: "DELETE",
            success: [function () {
                load();
                showCategories();
                showCards();
            }],
            error: [function () {
                alert("error!")
            }]
        })
    });

    $('#btn_sign_out').click(function () {
        let cookieUserId = $.cookie('userId');
        if (cookieUserId === undefined) {
            alert('Error!');
        } else {
            $.ajax({
                type: 'PUT',
                url: `user?id=${cookieUserId}`,
                success: [function () {
                    $(location).attr('href',
                        "http://localhost:8084/Card/login.html");
                }],
                error: [function (e) {
                    alert(JSON.stringify(e));
                }]
            });
        }
    });

    /**
     * -----------------------------------------Categories----------------------------------------------
     */
    $('#add_categories_button').click(function () {
        //let idCategory = parseInt($('#add_categories_id').val());
        let name = $('#add_categories_name').val();
        $.ajax({
            type: "POST",
            url: `categories?userId=${userId}`,
            data: JSON.stringify({"name": name}),
            success: [function (result) {
                //parseInt($('#add_categories_id').val(''));
                $('#add_categories_name').val('');
                showCategories();
                $('#tableCategories').val();
            }],
            error: [function (e) {
                alert(e.responseJSON.message);
            }]
        });
    });

    $('#listCategories tbody').on("click", "a", function () {
        let arr = $(this).attr('id').split('_');
        let id = parseInt(arr[1]);
        let value = arr[0];
        if (value === "change") {
            $('#modalChangeCategories').modal('show');
            $.ajax({
                type: "GET",
                url: `categories?id=${id}`,
                success: function (result) {
                    let categories = result.data;
                    $('#change_categories_id').val(categories.id);
                    $('#change_categories_name').val(categories.name);
                },
                error: function (e) {
                    alert(e.response (e.message));
                }
            });
        } else if (value === "delete") {
            deleteCategories(id);
        } else if (value === "shows") {
            categoryID = id
            showCards(categoryID);
        }
    });

    $('#change_categories_button').click(function () {
        let id = parseInt($('#change_categories_id').val());
        let name = $('#change_categories_name').val();
        $.ajax({
            type: "PUT",
            url: 'categories',
            data: JSON.stringify({
                "id": id, "name": name
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: [function (result) {
                showCategories();
            }],
            error: [function (e) {
                alert(e.responseJSON.message);
            }]
        });
    });

    /**
     * -----------------------------------------Cards---------------------------------------------------
     */

    $('#add_cards_button').click(function () {
        //let idCategory = parseInt($('#add_categories_id').val());
        let question = $('#add_cards_question').val();
        let answer = $('#add_cards_answer').val();
        $.ajax({
            type: "POST",
            url: `cards?categoryId=${categoryID}`,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify({"question": question, "answer": answer}),
            success: [function (result) {
                //parseInt($('#add_cards_id').val(''));
                $('#add_cards_question').val('');
                $('#add_cards_answer').val('');
                showCards(categoryID);
                $('#tableCards').val();
            }],
            error: [function (e) {
                alert(e.responseJSON.message);
            }]
        });
    });


    $('#listCards tbody').on("click", "a", function () {
        let arr = $(this).attr('id').split('_');
        let id = parseInt(arr[1]);
        let value = arr[0];
        if (value === "changeCard") {
            $('#modalChangeCards').modal('show');

            $.ajax({
                type: "GET",
                url: `cards?id=${id}`,
                success: function (result) {
                    let cards = result.data;
                    $('#change_cards_id').val(cards.id);
                    $('#change_cards_question').val(cards.question);
                    $('#change_cards_answer').val(cards.answer);
                },
                error: function (e) {
                    alert(e.responseJSON.message);
                }
            });
        } else if (value === "deleteCard") {
            deleteCards(id);
        } /*else if (value === "shows") {
            showCards(id);
        }*/


        $('#change_cards_button').click(function () {
            let id = parseInt($('#change_cards_id').val());
            let question = $('#change_cards_question').val();
            let answer = $('#change_cards_answer').val();
            $.ajax({
                type: "PUT",
                url: 'cards',
                data: JSON.stringify({
                    "id": id, "question": question, "answer": answer
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: [function (result) {
                    showCards(categoryID);
                }],
                error: [function (e) {
                    alert(e.responseJSON.message);
                }]
            });
        });
    });

});

function deleteCategories(id) {
    $.ajax({
        type: "DELETE",
        url: `categories?id=${id}`,
        success: [function (result) {
            showCategories();
        }],
        error: [function (e) {
            alert(e.responseJSON.message);
        }]
    });
}


function deleteCards(id) {
    $.ajax({
        type: "DELETE",
        url: `cards?id=${id}`,
        success: [function (result) {
            showCards(categoryID);
        }],
        error: [function (e) {
            alert(e.responseJSON.message);
        }]
    });
}









$(document).ready(function () {

})


