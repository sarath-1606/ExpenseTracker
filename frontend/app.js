
const API = "http://localhost:8080";


const form = document.getElementById("expenseForm");
const idEl = document.getElementById("expenseId");
const titleEl = document.getElementById("title");
const catEl = document.getElementById("categoryname");
const amountEl = document.getElementById("amount");
const dateEl = document.getElementById("expensedate");
const resetBtn = document.getElementById("resetBtn");


const tableBody = document.getElementById("expensesBody");
const filterCatEl = document.getElementById("filterCategory");
const filterBtn = document.getElementById("filterBtn");
const clearFilterBtn = document.getElementById("clearFilterBtn");


async function getJSON(url, options) {
  const res = await fetch(url, { headers: { "Content-Type": "application/json" }, ...options });
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.status === 204 ? null : res.json();
}


async function loadExpenses() {
  try {
    const items = await getJSON(`${API}/getallexpenses`);
    renderExpenses(items || []);
  } catch {
    renderExpenses([]);
  }
}


async function filterExpenses() {
  const cat = (filterCatEl.value || "").trim();
  if (!cat) return loadExpenses();
  try {
    const items = await getJSON(`${API}/${encodeURIComponent(cat)}`);
    renderExpenses(items || []);
  } catch {
    renderExpenses([]);
  }
}


function renderExpenses(items) {
  tableBody.innerHTML = "";
  if (!items.length) {
    const tr = document.createElement("tr");
    const td = document.createElement("td");
    td.colSpan = 6;
    td.textContent = "No expenses found.";
    tr.appendChild(td);
    tableBody.appendChild(tr);
    return;
  }

  items.forEach((exp) => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${exp.id || ""}</td>
      <td>${exp.title || ""}</td>
      <td>${exp.categoryname || ""}</td>
      <td>${exp.amount ?? 0}</td>
      <td>${exp.expensedate || ""}</td>
      <td class="actions"></td>
    `;
    const actions = tr.querySelector(".actions");

    const editBtn = document.createElement("button");
    editBtn.textContent = "Edit";
    editBtn.onclick = () => populateForm(exp);

    const delBtn = document.createElement("button");
    delBtn.textContent = "Delete";
    delBtn.onclick = () => deleteExpense(exp.id);

    actions.appendChild(editBtn);
    actions.appendChild(delBtn);
    tableBody.appendChild(tr);
  });
}


function populateForm(exp) {
  idEl.value = exp.id || "";
  titleEl.value = exp.title || "";
  catEl.value = exp.categoryname || "";
  amountEl.value = exp.amount ?? "";
  dateEl.value = exp.expensedate || "";
}


function clearForm() {
  idEl.value = "";
  titleEl.value = "";
  catEl.value = "";
  amountEl.value = "";
  dateEl.value = "";
}


async function saveExpense(event) {
  event.preventDefault();
  const payload = {
    title: (titleEl.value || "").trim(),
    categoryname: (catEl.value || "").trim(),
    amount: Number(amountEl.value),
    expensedate: dateEl.value,
  };
  const id = (idEl.value || "").trim();

  try {
    if (id) {
      await getJSON(`${API}/updateexpense/${encodeURIComponent(id)}`, {
        method: "PUT",
        body: JSON.stringify(payload),
      });
    } else {
      await getJSON(`${API}/addexpense`, {
        method: "POST",
        body: JSON.stringify(payload),
      });
    }
    clearForm();
    loadExpenses();
  } catch {
    alert("Save failed");
  }
}


async function deleteExpense(id) {
  if (!id) return;
  if (!confirm("Delete this expense?")) return;
  try {
    await fetch(`${API}/deleteexpense/${encodeURIComponent(id)}`, { method: "DELETE" });
    loadExpenses();
  } catch {
    alert("Delete failed");
  }
}


function init() {
  form.addEventListener("submit", saveExpense);
  resetBtn.addEventListener("click", clearForm);

  filterBtn.addEventListener("click", filterExpenses);
  clearFilterBtn.addEventListener("click", () => {
    filterCatEl.value = "";
    loadExpenses();
  });

  loadExpenses();
}

init();
