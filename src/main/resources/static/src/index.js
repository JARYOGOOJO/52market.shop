import axios from 'axios';
import $ from 'jquery'
import '@popperjs/core'
import 'bootstrap'
import './css/bootstrap.min.css';
import './css/main.css'
import './kakao'
import './aba5c3ead0';
import SockJS from 'sockjs-client'
import {Stomp} from '@stomp/stompjs'
import {addComment, callComments, chatView, getArticles, logInView, registerView, setModal} from './view';

let stompClient;
let userId = null;
Kakao.init("e1289217c77f4f46dc511544f119d102");

function setHeader() {
    let token = localStorage.getItem("token");
    axios.defaults.headers.common = {Authorization: `Bearer ${token}`}
}

function connect() {
    var socket = new SockJS(`${API_URL}/ws-stomp`);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe(`${API_URL}/topic/greetings`, function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

const genRandomName = length => {
    let name = '';
    let characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
    let charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
        let number = Math.random() * charactersLength;
        let index = Math.floor(number);
        name += characters.charAt(index);
    }
    return name;
}

const genLongNumber = length => {
    if (length < 1) return;
    let number = Math.random() * (10 ** (length));
    return Math.floor(number);
}

export function loginWithKakao() {
    Kakao.Auth.login({
        success: function (authObj) {
            console.log(authObj)
            axios.post(`${API_URL}/user/kakao`, { 'token': `${authObj['access_token']}` })
                .then(response => {
                    console.log(response)
                    localStorage.setItem("token", response.data['token']);
                    localStorage.setItem("userId", response.data['userId']);
                    location.hash = '';
                    setHeader();
                })
                .catch((err) => console.log(err))
        },
        fail: function (err) {
            alert(JSON.stringify(err))
        }
    })
}

export function login() {
    const email = $("#exampleInputEmail1").val();
    const password = $("#exampleInputPassword1").val();
    if (!(email && password)) {
        alert("올바른 아이디와 비밀번호를 입력해주세요.")
    }
    axios.post(`${API_URL}/user/signin`, {
        email: email,
        password: password,
    })
        .then(function (response) {
            console.log(response);
            const { data } = response;
            if (data) {
                localStorage.setItem("token", response.data['token']);
                localStorage.setItem("userId", response.data['userId']);
                location.hash = '';
            }
        })
        .catch(function (error) {
            console.log(error);
            alert("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.")
        });
    setHeader();
}

export function signup() {
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
    axios.post(`${API_URL}/user/signup`, {
        email: email,
        name: name,
        phoneNumber: phone,
        password: password,
        latitude: latitude,
        longitude: longitude
    })
        .then(function (response) {
            console.log(response);
            localStorage.setItem("token", response.data['token']);
            localStorage.setItem("userId", response.data['userId']);
            location.hash = '';
        })
        .catch(function (error) {
            console.log(error);
        });
    setHeader();
}

export const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/, '')
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

export const passwordOK = () => {
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

export const checkEmail = () => {
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

export function toggleComment(idx) {
    $(`#commentEdit-${idx}`).toggle('fade')
}


export const showWriteButton = () => {
    $("#articles-body").append(`
    <button class="btn btn-success"
        data-bs-target="#staticBackdrop"
        data-bs-toggle="modal" id="backDrop"
        type="button">write a post
    </button>`);
}

export function writeComment(idx) {
    userId = parseInt(localStorage.getItem("userId"));
    const content = $(`#commentWrite-${idx}`).val();
    console.log(content);
    const body = { articleId: idx, userId, content }
    axios.post(`${API_URL}/api/comment`, body)
        .then(({ data }) => {
            $(`#commentWrite-${idx}`).val("");
            addComment(idx, data)
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        });
}

export function letsMeet(idx, userId) {
    const body = {
        articleId: idx,
        commenterId: userId
    }
    axios.post(`${API_URL}/api/meet`, body)
        .then((response) => {
            console.log(response.data);
            location.hash = "chat";
        })
}

export function removeComment(idx, id) {
    axios.delete(`https://callmebyyourname.shop/api/comment/${id}`)
        .then(({ data }) => console.log(data))
        .then(() => {
            $(`#comment-list-${idx}`).empty();
            callComments(idx);
        })
}

export function editArticle(idx) {
    axios.get(`${API_URL}/api/article/${idx}`)
        .then(response => {
            let { id, title, content, user } = response.data;
            let answer = window.prompt("수정할 내용을 입력해주세요.", content)
            if (answer) {
                let send = { id, title, content: answer, userId };
                console.log(send)
                axios.put(`${API_URL}/api/article/edit`, send).then(() => location.reload());
            }
        })
}

export function deleteArticle(idx) {
    userId = parseInt(localStorage.getItem("userId"));
    axios
        .delete(`${API_URL}/api/article/${idx}`)
        .then(function (response) {
            console.log(response);
            location.reload();
        })
        .catch(function (error) {
            console.log(error);
            console.log("글 삭제에 실패했습니다.")
        })
}

export function Write() {
    userId = parseInt(localStorage.getItem("userId"));
    const title = $("#exampleFormControlInput1").val();
    const content = $("#exampleFormControlTextarea1").val();
    const image = $("#formFile")[0].files[0];
    const formData = new FormData();
    formData.append('userId', userId)
    formData.append('file', image)
    formData.append('title', title)
    formData.append('content', content)

    axios
        .post(`${API_URL}/api/article/write`, formData)
        .then(function (response) {
            console.log(response);
            window.location.href = "/";
        })
        .catch(function (error) {
            console.log(error);
            console.log("글 작성에 실패했습니다.")
        });
}

export function getCookie(name) {
    const value = "; " + document.cookie;
    const parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();

}

function extractParam(word) {
    return window.location.hash.split(word + "=").pop()
}

const router = () => {
    let path = location.hash.replace("#", "")
    switch (path) {
        case "":
            getArticles();
            setModal();
            setTimeout(() => showWriteButton(), 1000);
            break
        case "signup":
            registerView();
            break
        case "signin":
            logInView();
            break
        case "chat":
            chatView();
            break
    }
    if (path.startsWith("chat")) {
        chatView();
    }
    setHeader();
}

window.addEventListener('hashchange', router)

router();