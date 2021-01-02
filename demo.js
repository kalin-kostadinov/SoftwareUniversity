function solve(input) {

    let output = '<table>\n';

    output += '<tr>';

    let objArr = JSON.parse(input);

    let keys = Object.keys(objArr[0]);

    keys.forEach(key => output += `<th>${htmlEscape(key)}</th>`);

    output += '</tr>\n';


    for (let obj of objArr) {
        output += '<tr>';

        let values = Object.values(obj);

        values.forEach(value => output += `<td>${htmlEscape(value)}</td>`)

        output += '</tr>\n';
    }
    output += '</table>';

    console.log(output);

    function htmlEscape(param) {

        if (typeof param == 'number') {
            return param;
        }

        let result = '';

        for (let i = 0; i < param.length; i++) {
            switch (param[i]) {
                case '&':
                    result += '&amp;';
                    break;
                case '<':
                    result += '&lt;';
                    break;
                case '>':
                    result += '&gt;';
                    break;
                case '"':
                    result += '&quot;';
                    break;
                case '\'':
                    result += '&#39;';
                    break;
                default:
                    result += param[i];
                    break;
            }
        }

        return result;

    };

}
solve(
    ['[{"Name":"Tomatoes & Chips","Price":2.35},{"Name":"J&B Chocolate","Price":0.96}]']


);