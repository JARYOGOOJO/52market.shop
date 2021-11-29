function login() {
    const name = $("#exampleInputEmail1").val();
    const password = $("#exampleInputPassword1").val();
    axios
        .post("http://localhost:8080/api/signin", {
            name: name,
            password: password,
        })
        .then(function (response) {
            console.log(response);
            const {data} = response;
            if (data !== null && data !== "") {
                const {id, name} = data;
                console.log("id", id, "name", name);
                localStorage.setItem(
                    "user",
                    JSON.stringify({id: id, name: name})
                );
                const User = JSON.parse(localStorage.getItem("user"));
                console.log("User", User);
                window.location.href = "/";
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}