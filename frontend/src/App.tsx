import NotificationButton from "./components/NotificationButton"
import Header from "./components/Header/index"
import SalesCard from "./components/SalesCard/index"

function App() {
  return(
    <>
      <Header />
      <main>
        <section id="sales">
          <div className="dsmeta-container">
              <SalesCard />
          </div>
        </section>
      </main>
    </>
  )
}

export default App
