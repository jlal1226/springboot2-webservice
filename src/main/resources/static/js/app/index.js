// index.js만의 유호범위를 만듬
// main 객체를 만들어 해당 객체에서 필요한 모든 funtion을 선언하는 방법
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val(),
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // 글 등록 성공하면 메인페이지(/)로 돌아감
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};
main.init();