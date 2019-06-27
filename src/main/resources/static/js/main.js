const send_form = (form, method, forward) => {
  const notice = document.querySelector('#notice');
  const body = new URLSearchParams();
  Array.from(form).forEach(ele => body.append(ele.name, ele.value));
  fetch(form.action, {
    method: method,
    body: body
  }).then(response => response.json()).then(data => {
    if (data.code === 1) {
      if (forward) {
        location.replace(forward);
      }
    } else {
      if (notice) {
        notice.innerText = data.msg;
      }
    }
  }).catch(e => console.log(e))
};

const changePassword = (form, method, forward) => {
  const notice = document.querySelector('#notice');
  const body = new URLSearchParams();
  Array.from(form).forEach(ele => body.append(ele.name, ele.value));
  fetch(form.action, {
    method: method,
    body: body
  }).then(response => response.json()).then(data => {
    if (data.code === 1) {
      if (forward) {
        location.replace(forward);
      }
    } else {
      if (notice) {
        notice.innerText = data.msg;
      }
    }
  }).catch(() => location.replace('login.html'))
};
const deleteN = (url, id) =>
    fetch(`/${url}/${id}`, {
      method: 'delete'
    }).then(() => location.reload());

const logout = () => {
  fetch('/user/session', {
    method: 'delete'
  }).then(response => response.json()).then(
      json => json.code === 1 ? window.location.replace('index.html')
          : '').catch(e => console.log(e))
};

const modifyNB = (modifier, nbId, originVal) => {
  const title = prompt('标题修改为？', originVal);
  if (title === null || title === originVal) {
    return false;
  }
  const params = new URLSearchParams();
  params.append('nbId', nbId);
  params.append(modifier, title);
  fetch(`/notebook/${modifier}`, {
    method: 'put',
    body: params
  }).then(res => res.json()).then(
      json => json.code === 1 ? location.reload() : '').catch(
      () => location.replace('login.html'))
};

const getQueryStringParameters = () => {
  let query = window.location.search.substring(1);
  return (/^[?#]/.test(query) ? query.slice(1) : query)
  .split('&')
  .reduce((params, param) => {
        let [key, value] = param.split('=');
        params[key] = value ? decodeURIComponent(value.replace(/\+/g, ' '))
            : '';
        return params;
      },
      {}
  )
};