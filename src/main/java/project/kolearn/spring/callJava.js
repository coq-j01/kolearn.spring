//
//    document.addEventListener("DOMContentLoaded", function() {
//                fetch('/main')
//                    .then(response => response.text())
//                    .then(message => {
//                        document.getElementById("output").innerHTML = message;
//                    })
//                    .catch(error => {
//                        console.error('Error:', error);
//                    });
//            });
//
function fetchRandomGrammar() {
    fetch('/randgram')
        .then(response => response.text())
        .then(data => {
            document.getElementById("output").innerHTML = data;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// 페이지 로딩이 완료되면 자동으로 호출
document.addEventListener("DOMContentLoaded", fetchRandomGrammar);

