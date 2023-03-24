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
            window.location.href = "login"
        } else {
            alert(data.reason)
            window.location.href = "register"
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

function updateClubRow(cont, clubid) {
    let cell = document.getElementsByTagName('td')
    cell[parseInt(cont) * 4 + 1].innerHTML = "<input type='text' style=\"border:#16e4ff 1px solid\" size='15' value='" + cell[parseInt(cont) * 4 + 1].innerHTML + "' onblur='midclubname(this)'/>"
    cell[parseInt(cont) * 4 + 2].innerHTML = "<button onclick=updateClub(" + parseInt(clubid) + ")>保存</button>";

}

oldrname = ""
oldrage = ""
oldrclubname = ""
oldrole = ""

function updatePlayerRow(cont, playerid) {
    let cell = document.getElementsByTagName('td')
    oldname = cell[parseInt(cont) * 7 + 1].innerHTML;
    cell[parseInt(cont) * 7 + 1].innerHTML = "<input type='text' style=\"border:#16e4ff 1px solid\" size='15' value='" + cell[parseInt(cont) * 4 + 1].innerHTML + "' onblur='midplayername(this)'/>"
    oldage = cell[parseInt(cont) * 7 + 2].innerHTML;
    cell[parseInt(cont) * 7 + 2].innerHTML = "<input type='text' style=\"border:#16e4ff 1px solid\" size='15' value='" + cell[parseInt(cont) * 4 + 2].innerHTML + "' onblur='midplayerage(this)'/>"
    oldclubname = cell[parseInt(cont) * 7 + 3].innerHTML;
    cell[parseInt(cont) * 7 + 3].innerHTML = "<input type='text' style=\"border:#16e4ff 1px solid\" size='15' value='" + cell[parseInt(cont) * 4 + 3].innerHTML + "' onblur='midplayerclubid(this)'/>"
    oldrole = cell[parseInt(cont) * 7 + 4].innerHTML;
    cell[parseInt(cont) * 7 + 4].innerHTML = "<input type='text' style=\"border:#16e4ff 1px solid\" size='15' value='" + cell[parseInt(cont) * 4 + 4].innerHTML + "' onblur='midplayerrole(this)'/>"
    cell[parseInt(cont) * 7 + 5].innerHTML = "<button onclick=updatePlayer(" + parseInt(playerid) + ")>保存</button>";

}

function updatePlayer(playerid) {
    get('http://localhost:8080/updatePlayer',
        {
            playerid: playerid,
            playername: function () {
                if (newplayername === '')
                    return oldname;
                else return newplayername;
            },
            playerage: function () {
                if (newplayerage === '')
                    return oldage;
                else return newplayerage;
            },
            clubname: function () {
                if (newplayerclubname === '')
                    return oldclubname;
                else return newplayerclubname;
            },
            role: function () {
                if (newplayerrole === '')
                    return oldrole;
                else return newplayerrole;
            }
        }, function (data) {
            if (data.code === 200) {
                alert(data.reason)
                managePlayer();
            }
        })
}

newplayername = ""
newplayerage = ""
newplayerclubname = ""
newplayerrole = ""

function midplayername(inp) {
    newplayername = inp.value
}

function midplayerage(inp) {
    newplayerage = inp.value
}

function midplayerclubid(inp) {
    newplayerclubname = inp.value
}

function midplayerrole(inp) {
    newplayerrole = inp.value
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

function deletPlayer(playerid) {
    get('http://localhost:8080/deletPlayer', {playerid: playerid}, function (data) {
        if (data.code === 200) {
            alert(data.reason)
            managePlayer()
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

function showClub() {
    get('http://localhost:8080/manageClub', {}, function (data) {
        if (data.code === 200) {
            let orderClubData = data;
            let dataClubJson = JSON.stringify(orderClubData);
            location.href = encodeURI("showclub?dataClubJson=" + dataClubJson);
        }
    })
}

function showPlayer() {
    get('http://localhost:8080/managePlayer', {}, function (data) {
        let orderPlayerData = data[0];
        let dataPlayerJson = JSON.stringify(orderPlayerData);
        let orderClubData = data[1];
        let dataClubJson = JSON.stringify(orderClubData);
        location.href = encodeURI("showplayer?dataPlayerJson=" + dataPlayerJson + "&dataClubJson=" + dataClubJson);
    })
}

function managePlayer() {
    get('http://localhost:8080/managePlayer', {}, function (data) {
        let orderPlayerData = data[0];
        let dataPlayerJson = JSON.stringify(orderPlayerData);
        let orderClubData = data[1];
        let dataClubJson = JSON.stringify(orderClubData);
        location.href = encodeURI("player?dataPlayerJson=" + dataPlayerJson + "&dataClubJson=" + dataClubJson);
    })
}

function addPlayer() {
    get('http://localhost:8080/addplayerinjs',
        {
            playername: $("#addplayername").val(),
            playerage: $("#addplayerage").val(),
            playerclub: $("#selectplayergslaaa").val(),
            playerrole: $("#addplayerrole").val()
        }, function (data) {
            if (data.code === 200) {
                alert(data.reason)
                managePlayer();
            } else {
                alert(data.reason)
                managePlayer();
            }
        })
}
