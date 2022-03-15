function doPagging(block, page_index, total_pages, message, field, status, column, amount, url) {
    var sortColumn = '';
    if (field.length > 0 && status.length > 0) {
        sortColumn += '&field=' + field + '&sortIs=' + ((status === "DESC") ? "ASC" : "DESC") + '&column=' + column;
    }
    if (total_pages > 1) {
        var block_do_pagging = document.getElementById(block);
        var result = '<ul class="pagination">';

        //generate first
        if (page_index - amount > 1)
            result += '<li class="page-item"><a class="page-link" style="color: #555;" href="' + url + '?page=1&message=' + message + sortColumn + '"><strong>First</strong></a></li>';

        for (var i = page_index - amount; i < page_index; i++) {
            if (i >= 1) {
                result += '<li class="page-item"><a class="page-link" style="color: #555;" href="' + url + '?page=' + i + '&message=' + message + sortColumn + '">' + i + '</a></li>';
            }
        }

        //generate span for page_index
        result += '<li class="page-item""><a class="page-link" style="color: #fff; background-color:#353c48;"><strong>' + page_index + '</strong></a></li>';

        for (var i = page_index + 1; i <= page_index + amount; i++) {
            if (i <= total_pages) {
                result += '<li class="page-item"><a class="page-link" style="color: #555;" href="' + url + '?page=' + i + '&message=' + message + sortColumn + '">' + i + '</a></li>';
            }
        }

        //generate last
        if (page_index + amount < total_pages)
            result += '<li class="page-item"><a class="page-link" style="color: #555;" href="' + url + '?page=' + total_pages + '&message=' + message + sortColumn + '"><strong>Last</strong></a></li>';

        result += '</ul>';

        block_do_pagging.innerHTML = result;
    }
}