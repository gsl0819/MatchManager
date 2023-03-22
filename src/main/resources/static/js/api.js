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

function register() {
    get('http://localhost:8080/api/auth/register', {
        email: $("#input-email").val(),
        verify: $("#input-verify").val(),
        password: $("#input-password").val()
    }, function (data) {
        if (data.code === 200) {
            alert(data.reason)
            window.location = "login"
        } else {
            alert(data.reason)
            window.location = "register"
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

function updateClubRow(cont, userid) {
    let cell = document.getElementsByTagName('td')
    cell[parseInt(cont) * 4 + 1].innerHTML = "<input type='text' style=\"border:#16e4ff 1px solid\" size='15' value='" + cell[parseInt(cont) * 4 + 1].innerHTML + "' onblur='midclubname(this)'/>"
    cell[parseInt(cont) * 4 + 2].innerHTML = "<button onclick=updateClub(" + parseInt(userid) + ")>保存</button>";

}

newclubname = ''

function midclubname(inp) {
    newclubname = inp.value;
}

function updateClub(clubid) {
    get('http://localhost:8080/updatetClub', {clubid: clubid, clubname: newclubname}, function (data) {
        if (data.code === 200) {
            alert(data.reason)
            manageClub();
        }
    })
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

function manageClub() {
    get('http://localhost:8080/manageClub', {}, function (data) {
        if (data.code === 200) {
            let orderClubData = data;
            let dataClubJson = JSON.stringify(orderClubData);
            location.href = encodeURI("club?dataClubJson=" + dataClubJson);
        }
    })
}

function deletClub(clubid) {
    get('http://localhost:8080/deletClub', {clubid: clubid}, function (data) {
        if (data.code === 200) {
            manageClub()
        }
    })
}

function addClub() {
    get('http://localhost:8080/addclubinjs',
        {
            clubname: $("#addclubname").val()
        }, function (data) {
            if (data.code === 200) {
                alert(data.reason)
                manageClub();
            } else {
                alert(data.reason)
                manageClub();
            }
        })
}

function showClub(){
    get('http://localhost:8080/manageClub', {}, function (data) {
        if (data.code === 200) {
            let orderClubData = data;
            let dataClubJson = JSON.stringify(orderClubData);
            location.href = encodeURI("showclub?dataClubJson=" + dataClubJson);
        }
    })
}