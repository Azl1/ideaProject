/*function showUsers(){
    $.ajax({
        type: "GET",
        url: 'user',
        success: [function (result) {
            let users = result.data;
            let res = '';
            for (let i = 0; i < users.length; i++) {
                let user = users[i];
                res += `${user.id}, ${user.name}, ${user.age}<br>`;
            }
            $('#users').html(res);
        }],
        error: [function (e) {
            alert("error");
            alert(JSON.stringify(e));
        }]
    });
}*/

function showUsers() {
    $('#table_users tbody').html('');

    $.ajax({
        type: "GET",
        url: 'user',
        success: [function (result) {
            let users = result.data;
            let res = '';
            for (let i = 0; i < users.length; i++) {
                let user = users[i];
                res += `<tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                        </tr>`;
            }
            $('#table_users tbody').html(res);

        }], error: [function (e) {
            alert("error");
            alert(JSON.stringify(e));
        }]
    });

}

$(document).ready(function () {
    showUsers();

    $('#button_save').click(function () {
        let age = parseInt($('#input_age').val());
        let name = $('#input_name').val();

        $.ajax({
            type: "POST",
            url: 'user',
            data: JSON.stringify({"age": age, "name": name}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: [function (result) {
                $('#input_age').val('');
                $('#input_name').val('');
                showUsers();
            }],
            error: [function () {
                alert("error");
            }]
        });
    });

    $('#button_update').click(function () {
        let id = parseInt($('#input_id').val());
        let age = parseInt($('#input_age').val());
        let name = $('#input_name').val();

        $.ajax({
            type: "PUT",
            url: 'user',
            data: JSON.stringify({"id": id, "age": age, "name": name}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: [function (result) {
                $('#input_id').val('');
                $('#input_age').val('');
                $('#input_name').val('');
                showUsers();
            }],
            error: [function () {
                alert("error");
            }]
        });
    });

    $('#button_delete').click(function () {
        let id = parseInt($('#input_id').val());

        $.ajax({
            type: "DELETE",
            url: 'user?id=' + id,

            contentType: "application/json; charset=utf-8",

            success: [function (result) {
                $('#input_id').val('');
                $('#input_age').val('');
                $('#input_name').val('');
                showUsers();
            }],
            error: [function () {
                alert("error");
            }]
        });
    });

});