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
    get('http://localhost:8080/deletUser', {userid:userid}, function (data) {
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
        async: true,
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
        async: true,
        data: data,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: success
    });
}