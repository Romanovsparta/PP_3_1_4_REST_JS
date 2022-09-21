const deleteButton = document.querySelector('.btn-danger')
const editButton = document.querySelector('.btn-primary')
const navbar = document.querySelector('.navbar-brand')
const apiUrl = 'http://localhost:8080/admin/api'
let editButtonIsPressed = false
let delButtonIsPressed = false
let output = ''
let children
let parent
let delId

const renderUser = (user) => {
    table.innerHTML += `
    <tr>
        <td>${user.id}</td>
        <td><p>${user.firstName}</p></td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${user.role}</td>
        <td><button type="button" class="btn btn-info" data-toggle="modal" id="edit" 
                data-target="#modalEdit"> Edit</button></td>
        <td><button type="button" class="btn btn-danger" data-toggle="modal" id="delete" 
                data-id=${user.id} data-target="#modalDelete"> Delete</button></td>
    </tr>
    `
}

const renderUsers = (users) => {
    users.forEach(user => {
        output += `
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td><button type="button" class="btn btn-info" data-toggle="modal" id="edit" 
                    data-target="#modalEdit"> Edit</button></td>
            <td><button type="button" class="btn btn-danger" data-toggle="modal" id="delete" 
                    data-id=${user.id} data-target="#modalDelete"> Delete</button></td>
        </tr>
        `
    })
    table.innerHTML = output
}

fetch(apiUrl + '/auth')
    .then(res => res.json())
    .then(admin => {
        navbar.innerHTML = `<b>${admin.email}</b> with roles: ${admin.role}`
        adminInfo.innerHTML = `
        <tr>
            <td>${admin.id}</td>
            <td>${admin.firstName}</td>
            <td>${admin.lastName}</td>
            <td>${admin.age}</td>
            <td>${admin.email}</td>
            <td>${admin.role}</td>
        </tr>
        `
    })

fetch(apiUrl + '/users')
    .then(res => res.json())
    .then(data => renderUsers(data))

addUserForm.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: newFirstName.value,
            lastName: newLastName.value,
            age: newAge.value,
            email: newEmail.value,
            password: newAge.value,
            role: $('#newUserRole').val().join(' ')
        })
    })
        .then(res => res.json())
        .then(data => {renderUser(data)})
        .then(addUserForm.reset())
    usersTable.click()
})

table.addEventListener('click', (e) => {
    e.preventDefault()
    editButtonIsPressed = e.target.id == 'edit'
    delButtonIsPressed = e.target.id == 'delete'
    delId = e.target.dataset.id
    parent = e.target.closest('tr')
    children = parent.children

    if (delButtonIsPressed) {
        deleteId.value = children[0].textContent
        deleteFirstName.value = children[1].textContent
        deleteLastName.value = children[2].textContent
        deleteAge.value = children[3].textContent
        deleteEmail.value = children[4].textContent
        deleteRoles.value = children[5].textContent
    }

    if (editButtonIsPressed) {
        editId.value = children[0].textContent
        editFirstName.value = children[1].textContent
        editLastName.value = children[2].textContent
        editAge.value = children[3].textContent
        editEmail.value = children[4].textContent
        editRoles.value = children[5].textContent
    }
})

deleteButton.addEventListener('click', (e) => {
    e.preventDefault()
    fetch(`${apiUrl}/${delId}`, {
        method: 'DELETE'
    })
    parent.innerHTML = ''
})

editButton.addEventListener('click', (e) => {
    e.preventDefault()
    fetch(apiUrl, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: editId.value,
            firstName: editFirstName.value,
            lastName: editLastName.value,
            age: editAge.value,
            email: editEmail.value,
            password: editPassword.value,
            role: $('#editRoles').val().join(' ')
        })
    })
    children[1].textContent = editFirstName.value
    children[2].textContent = editLastName.value
    children[3].textContent = editAge.value
    children[4].textContent = editEmail.value
    children[5].textContent = $('#editRoles').val().join(' ')
})