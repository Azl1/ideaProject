


function getMinCountParts(parts) {
    let minValue = 1000000;
    for (let i = 0; i < parts.length; i++) {
        if (parts[i].count < minValue && parts[i].need === true) {
            minValue = parts[i].count;
        }
    }
    return minValue;
}

function showParts() {
    $('#pagination tbody').html("");
    $.ajax({
        type: "GET",
        url: 'part',
        success: [function (result) {
            let parts = result.data;
            for (let i = 0; i < parts.length; i++) {
                let markup = "<tr>" +
                    "<td>" + parts[i].name + "</td>" +
                    "<td>" + (parts[i].need ? "Yes" : "No") + "</td>" +
                    "<td>" + parts[i].count + "</td>"
                    + `<td style="text-align: center"><a href="#" id="change_${parts[i].id}"><i class="fa fa-edit" style="font-size:20px"></i></a></td>`
                    + `<td style="text-align: center"><a href="#" id="delete_${parts[i].id}"><i class="fa fa-trash" style="font-size:20px"></i></a></td>`;
                $("table tbody").append(markup);
            }
            let res = getMinCountParts(parts);
            $('#counts').html(`Minimal count parts: ${res}`);
            pagination();

        }],
        error: [function (e) {
            alert("error");
            alert(JSON.stringify(e));
        }]
    });
}

$(document).ready(function () {
    $('#add_part_button').click(function () {
        let name = $('#add_part_name').val();
        let need = $('#add_part_need').prop('checked');
        let count = parseInt($('#add_part_count').val());
        $.ajax({
            type: "POST",
            url: 'part',
            data: {"name": name, "need": need, "count": count},
            success: [function (result) {
                $('#add_part_name').val('');
                $('#add_part_need').prop('');
                $('#add_part_count').val('');
                showParts();
            }],
            error: [function () {
                alert("error");
            }]
        });
    });

    $('tbody').on("click", "a", function () {
        let arr = $(this).attr('id').split('_');
        let id = parseInt(arr[1]);
        let value = arr[0];
        if (value === "change") {
            $('#modalChangePart').modal('show');
            //TODO set part's data to ChangePartModalWindow
            $.ajax({
                type: "GET",
                url: `part?id=${id}`,
                success: function (result) {
                    let parts = result.data;
                    $('#change_part_id').val(parts.id);
                    $('#change_part_name').val(parts.name);
                    $('#change_part_need').prop('checked', parts.need);
                    $('#change_part_count').val(parts.count);
                },
                error: function (e) {
                    alert("Ошибка при загрузке данных: " + JSON.stringify(e));
                }
            });
        } else {
            deletePart(id);
        }
    });

    //TODO обработчик кнопки

    $('#change_part_button').click(function () {
        let id = parseInt($('#change_part_id').val());
        let name = $('#change_part_name').val();
        let need = $('#change_part_need').prop('checked');
        let count = parseInt($('#change_part_count').val());

        $.ajax({
            type: "PUT",
            url: 'part',
            data: JSON.stringify({
                "id": id, "name": name, "need": need, "count": count
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: [function (result) {
                showParts();
            }],
            error: [function (e) {
                alert(JSON.stringify(e));
                alert("error");
            }]
        });
    });

    $('#search_part_button').click(function () {
        let name = $('#search_part_name').val();
        let need = $('#search_part_need').prop('checked');
        let count = parseInt($('#search_part_count').val());
        $.ajax({
            type: "GET",
            url: `part?name=${name}&need=${need}&count=${count}`,
            success: [function (result) {
                $("tbody").html("");
                let partsSearch = result.data;
                for (let i = 0; i < partsSearch.length; i++) {
                    let markup = "<tr>" +
                        "<td>" + partsSearch[i].name + "</td>" +
                        "<td>" + (partsSearch[i].need ? "Yes" : "No") + "</td>" +
                        "<td>" + partsSearch[i].count + "</td>"
                        + `<td style="text-align: center"><a href="#" id="change_${partsSearch[i].id}"><i class="fa fa-edit" style="font-size:20px"></i></a></td>`
                        + `<td style="text-align: center"><a href="#" id="delete_${partsSearch[i].id}"><i class="fa fa-trash" style="font-size:20px"></i></a></td>`;
                    $("table tbody").append(markup);


                }
                pagination();

            }],
            error: [function (e) {
                console.error(e);
                alert(JSON.stringify(e));
                alert("error");
            }]
        });

    });

});

function deletePart(id) {
    $.ajax({
        type: "DELETE",
        url: `part?id=${id}`,
        success: [function (result) {
            showParts();
        }],
        error: [function (e) {
            alert(JSON.stringify(e));
        }]
    });
}

//TODO calculate, how many computers will produce using given parts
// подсчитайте, сколько компьютеров будет произведено
// с использованием данных деталей
// минимальное count среди need которое true


//TODO make search_part_button event handler
// сделать обработчик событий search_part_button
// по нажатию на кнопку  сделать гет запрос на сервер с передачей нейм нид каунт,
// получить список результат и отобразить его в таблице как в функции showparts