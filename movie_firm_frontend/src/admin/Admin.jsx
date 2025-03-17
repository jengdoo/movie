import React from "react";
import { Layout } from "antd";
import { Routes, Route } from "react-router-dom";
import Sidebar from "./component/slidebar";
import Promotion from "./pages/promotion";
import Movies from "./pages/movie";
import User from "./pages/user";
import Payment from "./pages/payment";
import Vouchers from "./pages/voucher";

const { Header, Content } = Layout;

const AdminApp = () => {
  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Sidebar />
      <Layout>
        <Content
          style={{ margin: "20px", background: "#fff", padding: "20px" }}
        >
          <Routes>
            <Route path="/admin/promotions" element={<Promotion />} />
            <Route path="/admin/movies" element={<Movies />} />
            <Route path="/admin/users" element={<User />} />
            <Route path="/admin/payments" element={<Payment />} />
            <Route path="/admin/vouchers" element={<Vouchers />} />
          </Routes>
        </Content>
      </Layout>
    </Layout>
  );
};

export default AdminApp;
