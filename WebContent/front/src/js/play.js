/**
 * 将歌曲添加到播放 列表 并播放
 * @param {Object} String songIds
 */
function addPlayListAndPlay(songIds) {
	$.ajax({
		url: "http://localhost:8080/OMmusic/playList/addListAndPlay",
		data: {
			"songIdStrings": songIds
		},
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		type: "GET",
		success: function(data) {
			console.log(data);
			if (data.success == false) {
				alert(data.msg);
				return;
			}
			window.location.href = "http://localhost:8080/OMmusic/front/play.jsp?songIndex="+data.songIndex;
		}
	});
}

/**
 * 将歌曲添加到播放列表
 * @param {Object} songIds
 */
function addPlayList(songIds) {
	$.ajax({
		url: "http://localhost:8080/OMmusic/playList/addListAndPlay",
		data: {
			"songIdStrings": songIds
		},
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		type: "GET",
		success: function(data) {
			console.log(data);
			if (data.success == false) {
				alert(data.msg);
				return;
			}
			alert("添加成功！");
			// window.location.href = "http://localhost:8080/OMmusic/front/play.jsp?songIndex=-1";
		}
	});
}
