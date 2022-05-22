package ir.mmd.intellijdev.easysolution.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefBrowser
import ir.mmd.intellijdev.easysolution.data.SEARCH_STACKOVERFLOW
import ir.mmd.intellijdev.easysolution.util.urlEncoded

class SearchInStackOverflow : AnAction() {
	companion object {
		private const val TOOL_WINDOW_ID = "StackOverflow"
	}
	
	override fun actionPerformed(e: AnActionEvent) {
		val project = e.project!!
		val editor = e.getRequiredData(CommonDataKeys.EDITOR)
		val searchTerm = editor.caretModel.primaryCaret.selectedText!!
		val toolWindowManager = ToolWindowManager.getInstance(project)
		val contentFactory = ContentFactory.SERVICE.getInstance()
		
		val toolWindow = toolWindowManager.getToolWindow(TOOL_WINDOW_ID) ?: toolWindowManager.registerToolWindow(
			TOOL_WINDOW_ID,
			true,
			ToolWindowAnchor.RIGHT
		)
		
		toolWindow.contentManager.addContent(
			contentFactory.createContent(
				JBCefBrowser("$SEARCH_STACKOVERFLOW${searchTerm.urlEncoded}").component,
				searchTerm,
				false
			)
		)
		
		toolWindow.show()
	}
	
	override fun update(e: AnActionEvent) {
		val editor = e.getData(CommonDataKeys.EDITOR)
		e.presentation.isEnabled = editor != null && editor.caretModel.primaryCaret.hasSelection()
	}
}