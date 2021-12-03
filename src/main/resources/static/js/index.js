function login() {
    const email = $("#exampleInputEmail1").val();
    const password = $("#exampleInputPassword1").val();
    if (!(email && password)) {
        alert("올바른 아이디와 비밀번호를 입력해주세요.")
    }
    axios.post("http://localhost:8080/api/signin", {
        email: email,
        password: password,
    })
        .then(function (response) {
            console.log(response);
            const { data } = response;
            if (data) {
                const { id, email } = data;
                console.log("id", id, "email", email);
                localStorage.setItem(
                    "user",
                    JSON.stringify({ id: id, email: email })
                );
                const User = JSON.parse(localStorage.getItem("user"));
                console.log("User", User);
                email
                window.location.href = "/";
            }
        })
        .catch(function (error) {
            console.log(error);
            alert("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.")
        });
}

function signup() {
    let latitude = 37.49798901601007;
    let longitude = 127.03796438656106;
    navigator
        .geolocation
        .getCurrentPosition((position) => {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
        });
    const email = $("#exampleInputEmail1").val();
    const name = $("#inputDefault").val();
    const phone = $("#phoneDefault").val();
    const password = $("#exampleInputPassword1").val();
    const repassword = $("#exampleInputPassword2").val();
    if (password !== repassword) return;
    axios.post("http://localhost:8080/api/signup", {
        email: email,
        name: name,
        phoneNumber: phone,
        password: password,
        latitude: latitude,
        longitude: longitude
    })
        .then(function (response) {
            console.log(response);
            const { data } = response;
            const { id, name } = data;
            console.log(id, name)
            //   console.log("id", id, "name", name);
            //   localStorage.setItem(
            //     "user",
            //     JSON.stringify({ id: id, name: name })
            //   );

            //   const User = JSON.parse(localStorage.getItem("user"));
            //   console.log("User", User);
            window.location.href = "/login.html";
        })
        .catch(function (error) {
            console.log(error);
        });
}

const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/, '')
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

const passwordOK = () => {
    const password = $("#exampleInputPassword1").val();
    const repassword = $("#exampleInputPassword2").val();
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (!regex.test(password)) {
        $("#pwdHelp").text("비밀번호 양식이 올바르지 않습니다. (영문,소문자 8자 이상)");
        $("#submit").attr("disabled", true);
    } else if (repassword && (password !== repassword)) {
        $("#repwdHelp").text("비밀번호와 확인이 일치하지 않습니다.");
        $("#submit").attr("disabled", true);
    } else {
        $("#pwdHelp").text("");
        $("#repwdHelp").text("");
        activate();
    }
}

const checkEmail = () => {
    const email = $("#exampleInputEmail1").val();
    const regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!regex.test(email)) {
        $("#emailHelp").text("이메일 형식이 올바르지 않습니다.");
        $("#submit").attr("disabled", true);
    } else {
        $("#emailHelp").text("");
    }
}

const activate = () => {
    document.querySelectorAll("small").forEach(elem => {
        if (!elem.text) $("#submit").removeAttr("disabled")
    });
}

const getArticles = () => {
    const User = JSON.parse(localStorage.getItem("user"));
    if (User !== null) {
        $("#name").text(User.name + "님 환영합니다.");
    }
    axios
        .get("http://localhost:8080/api/articles")
        .then(function (response) {
            // handle success
            console.log(response);
            const { data } = response;
            data.forEach((v, i) => {
                console.log(v);
                console.log(i);
                const { id, title, content, address, createdAt, user } = v;
                const { name } = user;
                console.log(title, content, address, name);
                const temp_html = `<!-- Card -->
                <div class="col-xs-12 col-sm-6 col-md-4 mx-auto">
                <div class="card" style="margin: 10px; min-width: 200px;">
                  <!--Card image-->
                  <div class="view overlay">
                      <img class="card-img-top" src="http://loremflickr.com/320/150?random=${i}"
                          alt="Card image cap">
                      <a href="#!">
                          <div class="mask rgba-white-slight"></div>
                      </a>
                  </div>
                  <!--Card content-->
                  <div class="card-body">
                      <!--Title-->
                      <h4 class="card-title">${title}</h4>
                      <!--Text-->
                      <p class="card-text">${content}</p>
                      <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                      <button onclick="console.log(this.title, ${id}, '${name}')" title="like" type="button" class="btn btn-primary">
                      <i class="far fa-thumbs-up"></i></button>
                      <button onclick="console.log(this.title, ${id}, '${name}')" title="comment" type="button" class="btn btn-primary">
                      <i class="fas fa-comments"></i></button>
                      <button onclick="console.log(this.title, ${id}, '${name}')" title="edit" type="button" class="btn btn-primary">
                      <i class="far fa-edit"></i></button>
                      <button onclick="console.log(this.title, ${id}, '${name}')" title="delete" type="button" class="btn btn-primary">
                      <i class="fas fa-trash-alt"></i></button>
                  </div>
              </div>
          </div>`;
                $("#articles-body").append(temp_html);
            });
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .then(function () {
            // always executed
        });
};

function boardUpdate(article_id) {
    localStorage.setItem("article_id", article_id);
    window.location.href = "/update.html";
}

function boardDelete(article_id) {
    // alert("delete");
    const user = JSON.parse(localStorage.getItem("user"));
    axios
        .delete(
            `http://localhost:8080/api/article/${article_id}/${user.id}`,
            {}
        )
        .then(function (response) {
            console.log(response);
            window.location.href = "/";
        })
        .catch(function (error) {
            console.log(error);
        })
        .then(function () {
            // always executed
        });
}

function boardContent(article_id) {
    console.log("article_id", article_id);
    localStorage.setItem("article_id", article_id);
    window.location.href = "/detail.html";
    //   axios
    //     .get(`http://localhost:8080/api/article/${article_id}`, {})
    //     .then(function (response) {
    //       console.log(response);
    //     })
    //     .catch(function (error) {
    //       console.log(error);
    //     })
    //     .then(function () {
    //       // always executed
    //     });
}

function Write() {
    const User = JSON.parse(localStorage.getItem("user"));
    const title = $("#exampleFormControlInput1").val();
    const content = $("#exampleFormControlTextarea1").val();
    const image = $("#formFile")[0].files[0];
    console.log(User, title, content, image);

    axios
        .post("http://localhost:8080/api/article/write", {
            userId: User.id,
            title: title,
            content: content,
            image: image,
        })
        .then(function (response) {
            console.log(response);
            window.location.href = "/";
        })
        .catch(function (error) {
            console.log(error);
        });
}

function getCookie(name) {
    const value = "; " + document.cookie;
    const parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();
}