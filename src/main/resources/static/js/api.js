function askVerifycode() {
    get('http://localhost:8080/api/auth/verify-code', {
        email: $("#input-email").val()
    }, function (data) {
        if (data.code !== 200) {
            alert(data.reason)
        } else {
            alert(data.reason)
        }
    })
}

function logout() {
    get('http://localhost:8080/api/auth/logout', {}, function (data) {
        if (data.code === 200) {
            window.location = "login.html";
        }
    })
}

function manageUser() {
    get('http://localhost:8080/manageUser', {}, function (data) {
        if (data.code === 200) {
            let orderData = data;
            let dataJson = JSON.stringify(orderData);
            location.href = encodeURI("user?dataJson=" + dataJson);
        }
    })
}

function deletUser(userid) {
    get('http://localhost:8080/deletUser', {userid: userid}, function (data) {
        if (data.code === 200) {
            manageUser()
        }
    })
}

function get(url, data, success) {
    $.ajax({
        type: "get",
        data: data,
        url: url,
        async: false,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: success
    });
}

function post(url, data, success) {
    $.ajax({
        type: "post",
        url: url,
        async: false,
        data: data,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: success
    });
}

function updateRow(cont, userid) {
    let cell = document.getElementsByTagName('td')
    cell[parseInt(cont) * 6 + 1].innerHTML = "<input type='text' style=\"border:#16e4ff 1px solid\" size='15' value='" + cell[parseInt(cont) * 6 + 1].innerHTML + "' onblur='midname(this)'/>"
    cell[parseInt(cont) * 6 + 4].innerHTML = "<button onclick=updateUser(" + parseInt(userid) + ")>保存</button>";

}

newname = ""

function midname(inp) {
    newname = inp.value;
}

function updateUser(userid) {
    get('http://localhost:8080/updatetUser', {userid: userid, username: newname}, function (data) {
        if (data.code === 200) {
            alert(data.reason)
            manageUser()
        }
    })
}

function findRoleByUsername(username) {
    get('http://localhost:8080/findRoleByUsername', {username: username}, function (data) {
        if (data.code === 200) {
            document.cookie = "returnUserName = " + escape(data.reason);
        }
    })

}


