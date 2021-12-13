const getArticles = () => {
    let div = document.createElement("div");
    div.className = "card-deck";
    div.id = "articles-body"
    $("main > div").replaceWith(div);
    userId = parseInt(localStorage.getItem("userId"));
    axios
        .get(`${API_URL}/api/articles`)
        .then(function (response) {
            const {data} = response;
            data.forEach((article) => {
                const {id, title, content, user, imagePath, imageName} = article;
                const {name} = user;
                let temp_html = `<!-- Card -->
              <div class="col-xs-12 col-sm-6 col-md-4 mx-auto">
                  <div class="card" style="margin: 10px; min-width: 230px;">
                  <!--Card image-->
                  <div class="view overlay">
                  <img class="card-img-top" src="${imagePath}" alt="${imageName}"><a href="#!">
                  <div class="mask rgba-white-slight"></div>
                  </a></div>
                  <!--Card content-->
                  <div class="card-body">
                  <!--Title-->
                  <h5 class="card-title tit">${title}</h5>
                  <!--Text-->
                  <p class="card-text">${content}</p>
                  <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                  <button onclick="console.log(this.title, ${id}, '${name}')" title="like" type="button" class="btn btn-success">
                  <i class="far fa-thumbs-up"></i></button>
                  <button onclick="app.toggleComment(${id})" title="comment" type="button" class="btn btn-success">
                  <i class="fas fa-comments"></i></button>
                  {{__is_this_yours?__}}
                  </div>
                  <div id="commentEdit-${id}" class="input-group m-3 form-floating">
                  <input id="commentWrite-${id}" class="form-control" aria-describedby="button-addon2">
                  <label for="floatingInput">Leave a Comment...</label>
                  <button class="btn btn-success" onclick="app.writeComment(${id})" id="button-addon2">Button</button>
                  </div>
                  <ul class="list-group" id="comment-list-${id}">
                  </ul></div></div>`;
                const no_not_mine = "";
                const my_contents = `
                  <button onclick="app.editArticle(${id})" title="edit" type="button" class="btn btn-success">
              <i class="far fa-edit"></i></button>
              <button onclick="app.deleteArticle(${id})" title="delete" type="button" class="btn btn-success">
              <i class="fas fa-trash-alt"></i></button>`
                if (user.id === userId) {
                    $("#articles-body").append(temp_html.replace("{{__is_this_yours?__}}", my_contents));
                } else {
                    $("#articles-body").append(temp_html.replace("{{__is_this_yours?__}}", no_not_mine));
                }
                callComments(id);
                $(`#commentEdit-${id}`).hide();
            });
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        });
};

function setModal() {
    $("main").append(`
  <div aria-hidden="true" aria-labelledby="staticBackdropLabel" class="modal fade" data-bs-backdrop="static"
      data-bs-keyboard="false" id="staticBackdrop" tabindex="-1">
      <div class="modal-dialog">
          <div class="modal-content">
              <form action="" enctype="multipart/form-data" method="post">
                  <div class="modal-header">
                      <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                      <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"></button>
                  </div>
                  <div class="modal-body">

                      <div class="mb-3">
                          <label class="form-label" for="exampleFormControlInput1">Title</label>
                          <input class="form-control" id="exampleFormControlInput1" placeholder="share your story"
                              type="text">
                      </div>
                      <div class="mb-3">
                          <label class="form-label" for="exampleFormControlTextarea1">Content</label>
                          <textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
                      </div>
                      <input accept="image/*" class="form-control" id="formFile" name="u_file" type="file">
                  </div>
                  <div class="modal-footer">
                      <button class="btn btn-secondary" data-bs-dismiss="modal" type="button">Close</button>
                      <button class="btn btn-primary" onclick="app.Write();" type="button">Understood</button>
                  </div>
              </form>
          </div>
      </div>
  </div>`)
}

function registerView() {
    document.querySelector("main").innerHTML = `
  <div class="col-lg-3 col-sm-4 m-auto">
      <form action="" style="display: grid;">
          <div class="form-group">
              <label class="form-label mt-4" for="exampleInputEmail1">Email address</label>
              <input aria-describedby="emailHelp" class="form-control" id="exampleInputEmail1" oninput="app.checkEmail()"
                  placeholder="enter email" type="email">
              <small class="form-text text-muted" id="emailHelp"></small>
          </div>
          <div class="form-group">
              <label class="col-form-label-sm mt-2" for="inputDefault">Name</label>
              <input aria-describedby="nameHelp" class="form-control" id="inputDefault"
                  placeholder="tell us your name"
                  type="text">
              <small class="form-text text-muted" id="nameHelp"></small>
          </div>
          <div class="form-group">
              <label class="col-form-label-sm mt-2" for="phoneDefault">Phone</label>
              <input aria-describedby="phoneHelp" class="form-control" id="phoneDefault" oninput="app.autoHyphen(this)"
                  pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" placeholder="insert your phone number" type="tel">
              <small class="form-text text-muted" id="phoneHelp"></small>
          </div>
          <div class="form-group">
              <label class="form-label mt-2" for="exampleInputPassword1">Password</label>
              <input aria-describedby="pwdHelp" class="form-control" id="exampleInputPassword1" oninput="app.passwordOK()"
                  placeholder="password" type="password">
              <small class="form-text text-muted" id="pwdHelp"></small>
          </div>
          <div class="form-group">
              <label class="form-label-sm mt-2" for="exampleInputPassword2">re-Password</label>
              <input aria-describedby="repwdHelp" class="form-control" id="exampleInputPassword2"
                  oninput="app.passwordOK()" placeholder="confirm password" type="password">
              <small class="form-text text-muted" id="repwdHelp"></small>
          </div>
          <button class="btn mt-3 btn-lg btn-success" disabled id="submit" onclick="app.signup()" type="button">Register
          </button>
      </form>
      <a class="text-success" href="#signin">let me signin</a></div>`
}

function logInView() {
    document.querySelector("main").innerHTML = `
  <div class="col-lg-3 col-sm-4 m-auto">
  <form action="">
      <div class="form-group">
          <label class="form-label-sm mt-4" for="exampleInputEmail1">Email address</label>
          <input aria-describedby="emailHelp" class="form-control" id="exampleInputEmail1"
                 placeholder="Enter email" type="email">
          <small class="form-text text-muted" id="emailHelp"></small>
      </div>
      <div class="form-group">
          <label class="form-label-sm mt-2" for="exampleInputPassword1">Password</label>
          <input aria-describedby="pwdHelp" class="form-control" id="exampleInputPassword1"
                 onchange="app.passwordOK()" placeholder="Password" type="password">
          <small class="form-text text-muted" id="pwdHelp"></small>
      </div>
      <button class="btn mt-3 btn-lg login btn-login" disabled id="submit" onclick="app.login()" type="button">Login
      </button>
      <button class="btn mt-3 btn-lg login btn-kakao" id="custom-login-btn" onclick="app.loginWithKakao();">Kakao
          Login
      </button>
  </form>
  <a class="text-success" href="#signup">let me signup</a>
  </div>`;
}

function chatView() {
    document.querySelector("main").innerHTML = `
  <div class="chat_window">
      <div class="top_menu">
          <div class="buttons">
              <div class="button close"></div>
              <div class="button minimize"></div>
              <div class="button maximize"></div>
          </div>
          <div class="title">Chat</div>
      </div>
      <ul class="messages"></ul>
      <div class="bottom_wrapper clearfix">
          <div class="message_input_wrapper">
              <input class="message_input" placeholder="Type your message here..." onkeyup="app.send()" />
          </div>
          <div class="send_message" onclick="app.sendMessage()">
              <i class="icon fas fa-paper-plane"></i>
              <div class="text"> Send</div>
          </div>
      </div>
  </div>
  <div class="message_template">
      <li class="message">
          <div class="avatar"></div>
          <div class="text_wrapper">
              <div class="text"></div>
          </div>
      </li>
  </div>`
}

export {getArticles, setModal, registerView, logInView, chatView};