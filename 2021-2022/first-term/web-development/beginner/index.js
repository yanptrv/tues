const navigationTabs = document.getElementsByClassName('navigation-tab');
const tabs = document.getElementsByClassName('tab');

for (let navigationTab of navigationTabs) {
    navigationTab.onclick = (event) => {
        let id = event.target.id;

        for (let tab of tabs) {
            if (tab.classList.contains("active")) {
                tab.classList.remove("active");
            }
        }

        for (let tab of tabs) {
            if (tab.id === ("show-" + id)) {
                tab.classList.add("active");
            }
        }
    }
}