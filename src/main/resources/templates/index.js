$(".submitButton").click(function() {
    alert("start");
    $.ajax({
        type: "POST",
        url: "/doLogin",
        contentType: "application/json;charset=utf-8",
        data : {
            'username':document.getElementById("username").value,
            'password':document.getElementById("password").value
        },
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", token);
        },
        success: function(result) {
            alert(1);
            alert(result);
        }
    });
});