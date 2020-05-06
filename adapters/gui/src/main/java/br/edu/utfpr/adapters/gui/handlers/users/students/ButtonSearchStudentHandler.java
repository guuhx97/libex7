package br.edu.utfpr.adapters.gui.handlers.users.students;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.edu.utfpr.adapters.gui.views.users.students.SearchStudentView;
import br.edu.utfpr.libex7.application.domain.users.Student;
import br.edu.utfpr.libex7.application.ports.in.users.SearchUserUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public final class ButtonSearchStudentHandler implements EventHandler<ActionEvent> {
	

	private final SearchUserUseCase useCase;
    private final SearchStudentView view;
    
    
    
	public ButtonSearchStudentHandler(SearchUserUseCase useCase, SearchStudentView view) {
		this.useCase = useCase;
		this.view = view;
	}



	@SuppressWarnings("unchecked")
	@Override
	public void handle(ActionEvent event) {
		 try {
			 TextField txtName = view.getTxtName();
			 String name = txtName.getText();
			 
			 if(!StringUtils.isEmpty(name)) {
				 List<Student> students = (List<Student>)(List<?>) useCase.findByName(name);
				  TableView<Student> tableView = view.getTableView();
				 ObservableList<Student> observableList = (ObservableList<Student>) FXCollections.observableArrayList(students);
				 tableView.setItems(observableList);
			 }else {
				 List<Student> students = (List<Student>)(List<?>) useCase.findAll();
				  TableView<Student> tableView = view.getTableView();
				 ObservableList<Student> observableList = (ObservableList<Student>) FXCollections.observableArrayList(students);
				 tableView.setItems(observableList);
			 }
		 }catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR, "Erro ao consultar estudante");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		
	}
}
