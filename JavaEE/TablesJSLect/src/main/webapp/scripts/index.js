function showUsers() {
    $('#table_users tbody').html('');

    let res = '';
    res += `<tr>
            <td>1</td>
            <td>Petr</td>
            <td>Petrov</td>
            </tr>`;

    res += `<tr>
            <td>2</td>
            <td>Ivan</td>
            <td>Ivanov</td>
            </tr>`;

    $('#table_users tbody').html(res);
}

$(document).ready(function () {
    showUsers();
});