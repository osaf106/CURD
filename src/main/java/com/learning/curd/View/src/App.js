
import './App.css';
import {createBrowserRouter, RouterProvider} from 'react-router-dom'
import { AllUserGet } from './components/getUser/user';
import User_add from './components/addUser/user_add';
import UpdateUser from './components/update/updateUser';
function App() {

const router = createBrowserRouter([
  {
    path: "/",
    element: <AllUserGet/>
  },
  {
    path: "/add",
    element: <User_add/>
  },
  {
    path: "/edit/:id",
    element: <UpdateUser/>
  }
])



  return (
    <div className="App">
      <RouterProvider router={router}></RouterProvider>
    </div>
  );
}

export default App;
