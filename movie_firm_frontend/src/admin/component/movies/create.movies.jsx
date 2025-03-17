import React, { useState } from "react";
import {
  Form,
  Input,
  InputNumber,
  Switch,
  Select,
  Button,
  Upload,
  message,
} from "antd";
import { UploadOutlined } from "@ant-design/icons";
import axios from "axios";
import { createMovies } from "../../service/api.service";

const { Option } = Select;

const CreateMovieForm = ({ onSuccess }) => {
  const [form] = Form.useForm();
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (values) => {
    setLoading(true);
    try {
      const response = await createMovies({
        ...values,
        thumbnail: file, // Gửi file ảnh
      });

      message.success("Phim đã được tạo thành công!");
      form.resetFields();
      setFile(null);
      if (onSuccess) onSuccess(); // Gọi lại fetchMovies từ cha để cập nhật danh sách
    } catch (error) {
      message.error("Lỗi khi tạo phim!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Form form={form} layout="vertical" onFinish={handleSubmit}>
      <Form.Item
        name="title"
        label="Title"
        rules={[{ required: true, message: "Title is required!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        name="description"
        label="Description"
        rules={[{ required: true, message: "Description is required!" }]}
      >
        <Input.TextArea />
      </Form.Item>

      <Form.Item name="genre" label="Genre">
        <Input />
      </Form.Item>

      <Form.Item
        name="type"
        label="Type"
        rules={[{ required: true, message: "Type is required!" }]}
      >
        <Select>
          <Option value="MOVIE">Movie</Option>
          <Option value="SERIES">Series</Option>
        </Select>
      </Form.Item>

      <Form.Item name="isFree" label="Is Free?" valuePropName="checked">
        <Switch />
      </Form.Item>

      <Form.Item
        name="price"
        label="Price ($)"
        rules={[{ required: true, message: "Price is required!" }]}
      >
        <InputNumber min={0} />
      </Form.Item>

      <Form.Item name="releaseDate" label="Release Date">
        <Input />
      </Form.Item>

      <Form.Item
        name="director"
        label="Director"
        rules={[{ required: true, message: "Director is required!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item name="duration" label="Duration (minutes)">
        <InputNumber min={1} />
      </Form.Item>

      <Form.Item label="Thumbnail">
        <Upload
          beforeUpload={(file) => {
            setFile(file);
            return false;
          }}
          showUploadList={{ showRemoveIcon: true }}
          onRemove={() => setFile(null)}
        >
          <Button icon={<UploadOutlined />}>Chọn ảnh</Button>
        </Upload>
      </Form.Item>

      <Button type="primary" htmlType="submit" loading={loading}>
        Tạo phim
      </Button>
    </Form>
  );
};

export default CreateMovieForm;
