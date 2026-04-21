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
                    $(location).attr('href', "http://localhost:8082/RegistrationAuthentificationServletsLect/login.html");
                }],
                error: [function (e) {
                    alert(JSON.stringify(e));
                }]
            });
        }
    });
})


